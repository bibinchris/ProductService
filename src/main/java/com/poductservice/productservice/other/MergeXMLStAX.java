package com.poductservice.productservice.other;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.List;

public class MergeXMLStAX {
    public static void main(String[] args) throws Exception {
        List<String> inputFiles = List.of("file1.xml", "file2.xml", "file3.xml"); // Add more files as needed
        String outputFile = "merged.xml";

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter writer = outputFactory.createXMLEventWriter(new FileOutputStream(outputFile));
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        writer.add(eventFactory.createStartDocument());

        boolean headerWritten = false;
        writer.add(eventFactory.createStartElement("", "", "company"));

        for (String file : inputFiles) {
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));
            if (!headerWritten) {
                mergeHeader(reader, writer);
                headerWritten = true;
            }
            mergeEmployees(reader, writer);
            reader.close();
        }

        writer.add(eventFactory.createEndElement("", "", "company"));
        writer.add(eventFactory.createStartElement("", "", "Agent"));
        writer.add(eventFactory.createEndElement("", "", "Agent"));
        writer.add(eventFactory.createEndDocument());

        writer.close();
        System.out.println("Merged XML created successfully: " + outputFile);
    }

    private static void mergeHeader(XMLEventReader reader, XMLEventWriter writer) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("Header")) {
                writer.add(event);
                while (reader.hasNext()) {
                    event = reader.nextEvent();
                    writer.add(event);
                    if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("Header")) {
                        return;
                    }
                }
            }
        }
    }

    private static void mergeEmployees(XMLEventReader reader, XMLEventWriter writer) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("employee")) {
                writer.add(event);
                while (reader.hasNext()) {
                    event = reader.nextEvent();
                    writer.add(event);
                    if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("employee")) {
                        break;
                    }
                }
            }
        }
    }
}

