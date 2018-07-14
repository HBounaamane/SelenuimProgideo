package ProBook;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
 
public class Login_Assert_Test {
	// Déclaration des variables que nous allons utiliser dans ce script.
    String url = "http://probook.progideo.com";
    String expectedTitle1 = "(3) Fil d'actualités - ProBook";
    String actualTitle1 = null;
    String username = "hbounaamane";
    String password = "P@ssw0rd";
    String expectedTitle2 = "Fil d'actualités - ProBook";
    String actualTitle2 = null;
    WebDriver driver;
 
	@Test
	public void test() {
        // On clique sur le lien "Sign in / up".
        driver.findElement(By.linkText("Se connecter / s'inscrire")).click();
        // On récupère le titre de la page actuelle
        actualTitle1 = driver.getTitle();
        // On affiche dans le log un message d'information
        System.out.println("Login page title : expected value is "+expectedTitle1+" actual value is "+actualTitle1);
        // Si le test échoue, on affiche un message d'erreur 
        if (!actualTitle1.contentEquals(expectedTitle1)) {
            System.out.println("Test Failed");
        }
 
        // On saisit le username et le password
        driver.findElement(By.id("login_username")).sendKeys(username);
        driver.findElement(By.id("login_password")).sendKeys(password);
        // On clique sur le bouton "Sign in"
        driver.findElement(By.id("loginBtn")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("account-dropdown-link")));
        
        // On vérifie le titre de la page suite à la tentative de connexion
        actualTitle2 = driver.getTitle();
        // On affiche dans le log un message d'information
        Assert.assertEquals(actualTitle2, expectedTitle1,"Test Failed");
	}
	
	@BeforeMethod
	public void beforeMethod() {
        // Chemin vers le driver Gecko (pour Firefox uniquement)
        System.setProperty("webdriver.gecko.driver","C://geckodriver.exe");
        // Invocation du navigateur Firefox, qui sera identifié avec le nom "driver".
        driver = new FirefoxDriver();
        // Ouvrir la page "http://probook.progideo.com".
        driver.get(url);
	}
	
	@AfterMethod
	public void afterMethod() {
        // On ferme Firefox
        driver.close();
	}
 
}