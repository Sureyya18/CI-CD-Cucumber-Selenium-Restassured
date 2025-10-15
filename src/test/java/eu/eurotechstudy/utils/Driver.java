package eu.eurotechstudy.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

    /**
     * 1000 mit 1 Chrome = 10 Stunden
     * 1000 mit 10 Chrome = 1 Stunde
     *
     * WebDriver == Browser
     */

public class Driver {

    private Driver(){};
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();//null

    public static WebDriver getDriver(){
        if (driverPool.get() == null) {
            String browser = ConfigReader.get("browser");
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_leak_detection", false);
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--disable-popup-blocking");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-infobars");
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "chrome-headless":
                    driverPool.set(new ChromeDriver(new ChromeOptions().addArguments("--headless=new")));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox-headless":
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().addArguments("--headless=new")));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "edge":
                    if (System.getProperty("os.name").contains("MAC")) {
                        throw new WebDriverException("Your Operating System does not support Edge");
                    }
                    driverPool.set(new EdgeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("Your operating system does not support Internet Explorer");
                    }
                    driverPool.set(new InternetExplorerDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "safari":
                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your Operating System does not support Safari");
                    }
                    driverPool.set(new SafariDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;

                case "remote-chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("platformName", "ANY");
                    try {
                        driverPool.set(new RemoteWebDriver(new URL("http://192.168.1.5:4444"), chromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "remote-firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("platformName", "ANY");
                    try {
                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4444"), firefoxOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                default:
                    System.out.println("Invalid browser");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}

//public class Driver {
//    private Driver(){};
//    private static WebDriver driver;//null
//
//    public static WebDriver getDriver(){
//        if (driver == null) {
//            String browser = ConfigReader.get("browser");
//            switch (browser.toLowerCase()) {
//                case "chrome":
//                    ChromeOptions options = new ChromeOptions();
//                    Map<String, Object> prefs = new HashMap<>();
//                    prefs.put("credentials_enable_service", false);
//                    prefs.put("profile.password_manager_leak_detection", false);
//                    options.setExperimentalOption("prefs", prefs);
//                    options.addArguments("--disable-popup-blocking");
//                    options.addArguments("--disable-notifications");
//                    options.addArguments("--disable-extensions");
//                    options.addArguments("--disable-infobars");
//                    driver = new ChromeDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                    break;
//                case "chrome-headless":
//                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                    break;
//                case "firefox":
//                    driver = new FirefoxDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                    break;
//                case "firefox-headless":
//                    driver = new FirefoxDriver(new FirefoxOptions().addArguments("--headless=new"));
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                    break;
//                case "edge":
//                    if (System.getProperty("os.name").contains("MAC")) {
//                        throw new WebDriverException("Your Operating System does not support Edge");
//                    }
//                    driver = new EdgeDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                    break;
//                case "ie":
//                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
//                        throw new WebDriverException("Your operating system does not support Internet Explorer");
//                    }
//                    driver = new InternetExplorerDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                    break;
//                case "safari":
//                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
//                        throw new WebDriverException("Your Operating System does not support Safari");
//                    }
//                    driver = new SafariDriver();
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                    break;
//                case "remote-chrome":
//                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.setCapability("platform", Platform.ANY);
//                    try {
//                        driver = new RemoteWebDriver(
//                                new URL("http://localhost:4444"),
//                                chromeOptions);
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                    driver.manage().window().maximize();
//                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//                    break;
//                default:
//                    System.out.println("Invalid browser");
//            }
//        }
//        return driver;
//    }
//
//    public static void closeDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}

