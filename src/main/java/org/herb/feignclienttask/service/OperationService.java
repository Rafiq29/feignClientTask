package org.herb.feignclienttask.service;

import lombok.RequiredArgsConstructor;
import org.herb.feignclienttask.dto.request.OperationRequestDTO;
import org.herb.feignclienttask.dto.response.OperationResponseDTO;
import org.herb.feignclienttask.entity.Operation;
import org.herb.feignclienttask.mapper.OperationMapper;
import org.herb.feignclienttask.repo.OperationRepo;
import org.herb.feignclienttask.util.FeignServiceUtil;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepo repo;
    private final OperationMapper mapper;
    private final FeignServiceUtil serviceUtil;

    public double convert(OperationRequestDTO requestDTO) {
        double converted = 0;
        double value = 0.0;
        Optional<Operation> optional = repo.findAll().stream()
                .filter(op -> op.getCurr_date().equals(requestDTO.getCurr_date())
                        && op.getCurr().equals(requestDTO.getCurr()))
                .findFirst();
        if (optional.isEmpty()) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(
                        new InputSource(new StringReader(serviceUtil.getXML(requestDTO.getCurr_date()))));
                doc.getDocumentElement().normalize();
                NodeList nodes = doc.getElementsByTagName("Valute");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        if (element.getAttribute("Code").equals(requestDTO.getCurr().toUpperCase())) {
                            value = Double.parseDouble(
                                    element.getElementsByTagName("Value").item(0).getTextContent());
                            converted = requestDTO.getAmount() / value;
                            break;
                        }
                    }
                }
                Operation operation = mapper.toOperation(requestDTO);
                operation.setConvrt_amount(converted);
                operation.setValue(value);
                repo.save(operation);
            } catch (ParserConfigurationException | IOException | SAXException e) {
                throw new RuntimeException(e);
            }
        } else {
            Operation operation = optional.orElseThrow();
            converted = requestDTO.getAmount() / operation.getValue();
        }
        return converted;
    }

    public List<OperationResponseDTO> history() {
        return repo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
