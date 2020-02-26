package model;

import model.objects.Gun;
import model.objects.WeaponType;
import org.w3c.dom.*;
import org.xml.sax.Attributes;
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
     * UIController.java used variables
     */
    public static int amountOfCurrency;
    public static int levelNumber;
    public static int playerHealth;
    public static int playerSpeed;
    public static Gun selectedGun;

    /**
     * Array of players weapons *needs to be build on first load
     */
    public static Gun[] playerGuns;

    /**
     * Info for Chamber.java
     */
    public static double bossSpawnPerc = 0.05;
    public static boolean isBossInChamber;
    public static int maxNumOfOfficersAndGuards;

    /**
     * Loads all the saved variables in this file and set them directly
     */
    public static void loadData() {
        //load saved data
        //load file
        //if(file is null) {
        amountOfCurrency = 0;
        levelNumber = 1;
        playerHealth = 100;
        playerSpeed = 1;
        File file = new File(saveFilePath);
        if(!file.exists()){
            firstLoadSaveData();
        }
        LoadXMLObject();
    }

    private static void LoadXMLObject() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(LoadFile(saveFilePath ));
            Element elm = document.getDocumentElement();
            playerHealth = Integer.parseInt(elm.getAttribute("Health"));
            playerSpeed = Integer.parseInt(elm.getAttribute("Speed"));
            levelNumber = Integer.parseInt(elm.getAttribute("Level"));
            for (int i = 0; i < playerGuns.length; i++) {
                Node node = elm.getChildNodes().item(i);
                NamedNodeMap attr = node.getAttributes();

                playerGuns[i] = new Gun(
                        (int)Double.parseDouble(attr.getNamedItem("ReloadSpeed").getNodeValue()),
                        (int)Double.parseDouble(attr.getNamedItem("Damage").getNodeValue()),
                        (int)Double.parseDouble(attr.getNamedItem("Projectiles").getNodeValue()),
                        (int)Double.parseDouble(attr.getNamedItem("MagSize").getNodeValue()),
                        WeaponType.valueOf((attr.getNamedItem("Type").getNodeValue()))
                );
                System.out.println();
            }
        } catch (ParserConfigurationException te) {
            te.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private static void SaveXMLObject() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("player");
            root.setAttribute("Health", String.valueOf(playerHealth));
            root.setAttribute("Speed", String.valueOf(playerSpeed));
            root.setAttribute("Level", String.valueOf(levelNumber));
            root.setAttribute("Currency", String.valueOf(amountOfCurrency));
            document.appendChild(root);
            for (Gun gun : playerGuns) {
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
            StreamResult result = new StreamResult(new File("./Test.xml"));
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
        SaveXMLObject();
    }

    /**
     * Called only once, used to set values on first load
     */
    public static void firstLoadSaveData() {
        Gun ar = new Gun(25, WeaponType.AR);
        Gun smg = new Gun(25, WeaponType.SMG);
        Gun shotgun = new Gun(25, WeaponType.SHOTGUN);
        Gun sniper = new Gun(25, WeaponType.SNIPER);
        Gun rocketLauncher = new Gun(75, WeaponType.ROCKET_LAUNCHER);
        Gun rayGun = new Gun(25, WeaponType.RAY_GUN);
        playerGuns = new Gun[]{ar, smg, shotgun, sniper, rocketLauncher, rayGun};
        amountOfCurrency = 0;
        levelNumber = 1;
        playerHealth = 100;
        playerSpeed = 1;
        //create new file and save
        saveData();
    }
}
