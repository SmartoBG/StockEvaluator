package shoeEvaluator.dataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;


public class StockItemData {

    private static final String STOCK_ITEMS_FILE = "stock.xml";


    private static final String ITEM_ID = "itemID";
    private static final String CATEGORY = "category";
    private static final String SEASON = "season";
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String SIZE = "size";
    private static final String CONDITION = "condition";
    private static final String SEX = "sex";
    private static final String PRICE = "price";
    private static final String QUALITY = "quality";

    private ObservableList<StockItem> stockItems;

    public StockItemData() {
        stockItems = FXCollections.observableArrayList();
    }

    public ObservableList<StockItem> getStockItems(){
         return stockItems;
    }

    public void addStockItem(StockItem item){
        stockItems.add(item);
    }

    public void deleteStockItem(StockItem item){
        stockItems.remove(item);
    }

    public void loadStockItems() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(STOCK_ITEMS_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            // read the XML document
            StockItem stockItem = null;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a stockItem item, we create a new stockItem
                    if (startElement.getName().getLocalPart().equals(ITEM_ID)) {
                        stockItem = new StockItem();
                        event = eventReader.nextEvent();
                        stockItem.setItemId(event.asCharacters().getData());
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(CATEGORY)) {
                            event = eventReader.nextEvent();
                            stockItem.setCategory(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SEASON)) {
                        event = eventReader.nextEvent();
                        stockItem.setSeason(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(BRAND)) {
                        event = eventReader.nextEvent();
                        stockItem.setBrand(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(MODEL)) {
                        event = eventReader.nextEvent();
                        stockItem.setModel(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SIZE)) {
                        event = eventReader.nextEvent();
                        stockItem.setSize(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(CONDITION)) {
                        event = eventReader.nextEvent();
                        stockItem.setCondition(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SEX)) {
                        event = eventReader.nextEvent();
                        stockItem.setSex(event.asCharacters().getData());
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PRICE)) {
                        event = eventReader.nextEvent();
                        stockItem.setPrice(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(QUALITY)) {
                        event = eventReader.nextEvent();
                        stockItem.setQuality(event.asCharacters().getData());
                        continue;
                    }
                }

                // If we reach the end of a stockItem element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(ITEM_ID)) {
                        stockItems.add(stockItem);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveStockItems() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(STOCK_ITEMS_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement stockItemsStartElement = eventFactory.createStartElement("",
                    "", "stockItems");
            eventWriter.add(stockItemsStartElement);
            eventWriter.add(end);

            for (StockItem stockItem: stockItems) {
                saveStockItem(eventWriter, eventFactory, stockItem);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "stockItems"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Contacts file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing contact: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveStockItem(XMLEventWriter eventWriter, XMLEventFactory eventFactory, StockItem stockItem)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create stockItem open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", "Item");
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, ITEM_ID, stockItem.getItemId());
        createNode(eventWriter, CATEGORY, stockItem.getCategory());
        createNode(eventWriter, SEASON, stockItem.getSeason());
        createNode(eventWriter, BRAND, stockItem.getBrand());
        createNode(eventWriter, MODEL, stockItem.getModel());
        createNode(eventWriter, SIZE, String.valueOf(stockItem.getSize()));
        createNode(eventWriter, CONDITION, stockItem.getCondition());
        createNode(eventWriter, SEX, stockItem.getSex());
        createNode(eventWriter, PRICE, String.valueOf(stockItem.getPrice()));
        createNode(eventWriter, QUALITY, stockItem.getQuality());

        eventWriter.add(eventFactory.createEndElement("", "", "Item"));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

}