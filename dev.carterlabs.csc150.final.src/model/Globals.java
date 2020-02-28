package model;

import model.entities.Player;
import model.objects.Gun;
import model.objects.WeaponType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Random;

public class Globals {
    /**
     * Generals
     */
    public static Random rand = new Random();
    public static String saveFilePath = "./save.xml";

    /**
     *  Window Settings
     */
    public static int WIDTH = 1286;
    public static int HEIGHT = 733;
    /**
     * Player used variables
     */
    public static Player player = new Player(100, 1);
    public static int PLAYER_X = (WIDTH / 2 - 30);
    public static int PLAYER_Y = (HEIGHT / 2 - 10);

    /**
     * Info for Chamber.java
     */
    public static double bossSpawnPerc = 0.05;
    public static boolean isBossInChamber;
    public static int maxNumOfOfficersAndGuards = 3;
    public static boolean hasExited = false;

    /**
     * Loads all the saved variables in this file and set them directly
     */
    public static void loadData() {
        //load saved data
        File file = new File(saveFilePath);
        if(!file.exists()){
            firstLoadSaveData();
        }
        player = LoadXMLObject();
    }

    /**
     * loads the XML object from the file
     * @return the XML object
     */
    private static Player LoadXMLObject() {
        Player player = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(LoadFile(saveFilePath ));
            Element elm = document.getDocumentElement();
            player = new Player(
                    (int)Double.parseDouble(elm.getAttribute("Health")),
                    (int)Double.parseDouble(elm.getAttribute("Speed")),
                    (int)Double.parseDouble(elm.getAttribute("Currency")),
                    (int)Double.parseDouble(elm.getAttribute("Level"))
            );
            for (int i = 0; i < elm.getChildNodes().getLength(); i++) {
                Node node = elm.getChildNodes().item(i);
                NamedNodeMap attr = node.getAttributes();

                player.getGuns().add(new Gun(
                        (int)Double.parseDouble(attr.getNamedItem("ReloadSpeed").getNodeValue()),
                        (int)Double.parseDouble(attr.getNamedItem("Damage").getNodeValue()),
                        (int)Double.parseDouble(attr.getNamedItem("Projectiles").getNodeValue()),
                        (int)Double.parseDouble(attr.getNamedItem("MagSize").getNodeValue()),
                        WeaponType.valueOf((attr.getNamedItem("Type").getNodeValue()))
                ));
            }
        } catch (ParserConfigurationException te) {
            te.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    /**
     * Loads a file for an XML document
     * @param path Where the XML document is
     * @return The input source for the XML document parser
     */
    private static InputSource LoadFile(String path){
        String data = "";
        try(BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream(new File(path))))) {
            while(reader.ready()) {
                data += reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new InputSource(new StringReader(data));
    }

    /**
     * SaveXMLObject should fulfill the requirements of saving the player's state to a parsable XML document
     */
    private static void SaveXMLObject(Player player) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("player");
            root.setAttribute("Health", String.valueOf(player.getHealth()));
            root.setAttribute("Speed", String.valueOf(player.getSpeed()));
            root.setAttribute("Level", String.valueOf(player.getCurrentLevel()));
            root.setAttribute("Currency", String.valueOf(player.getCurrency()));
            document.appendChild(root);
            for (Gun gun : player.getGuns()) {
                Element gunElem = document.createElement("gun");
                gunElem.setAttribute("Damage", String.valueOf(gun.getDamage()));
                gunElem.setAttribute("ReloadSpeed", String.valueOf(gun.getReloadSpeed()));
                gunElem.setAttribute("MagSize", String.valueOf(gun.getMagSize()));
                gunElem.setAttribute("Projectiles", String.valueOf(gun.getProjectTileCount()));
                gunElem.setAttribute("Type", String.valueOf(gun.getWeaponType()));
                root.appendChild(gunElem);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File(saveFilePath));
            transformer.transform(domSource, result);
        } catch (TransformerException | ParserConfigurationException te) {
            te.printStackTrace();
        }
    }

    /**
     * Saves all the variables in this file to the save location
     */
    public static void saveData() {
        //Saves every value in this file except the generals
        SaveXMLObject(player);
    }

    /**
     * Called only once, used to set values on first load
     */
    public static void firstLoadSaveData() {
        player = new Player(100, 1, 0, 1);
        SaveXMLObject(player);
        //create new file and save
        saveData();
    }

    /**
     * For testing purposes
     * @param args
     */
    public static void main(String[] args) {
        Player p1 = new Player(100, 1, 0, 1);
        SaveXMLObject(p1);
        Player p = LoadXMLObject();
    }
}
