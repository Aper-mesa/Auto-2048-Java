import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Auto {
    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\Apermesa\\Documents\\GitHub\\Auto-2048-Java\\Resources\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();
        driver.get("https://play2048.co/classic");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement canvas = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#app > div.relative.flex.grow.select-none.items-stretch.pt-1\\.5.sm\\:gap-x-3.sm\\:px-3.md\\:pb-2.md\\:pt-0 > div.mx-auto.flex.min-w-0.max-w-screen-2xl.grow.basis-0.flex-col.items-stretch.gap-y-4.sm\\:gap-y-6 > div.flex.min-h-0.min-w-0.grow.basis-0.flex-col.items-center.justify-center.px-8.sm\\:min-h-60.sm\\:px-0 > div > canvas")
        ));

        Actions actions = new Actions(driver);

        // 模拟游戏操作直到结束
        while (true) {
            actions.sendKeys(canvas, Keys.ARROW_UP).perform();
            actions.sendKeys(canvas, Keys.ARROW_RIGHT).perform();
            actions.sendKeys(canvas, Keys.ARROW_DOWN).perform();
            actions.sendKeys(canvas, Keys.ARROW_LEFT).perform();

            if (isElementVisible(driver, By.cssSelector("button[autofocus='true']"))) {
                System.out.println("游戏结束！");
                printFinalScore(driver); // 打印最终得分
                break;
            }
        }

        //driver.quit();
    }

    private static boolean isElementVisible(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 打印最终得分
    private static void printFinalScore(WebDriver driver) {
        try {
            WebElement scoreElement = driver.findElement(By.cssSelector(
                    "#app > div.relative.flex.grow.select-none.items-stretch.pt-1\\.5.sm\\:gap-x-3.sm\\:px-3.md\\:pb-2.md\\:pt-0 > div.mx-auto.flex.min-w-0.max-w-screen-2xl.grow.basis-0.flex-col.items-stretch.gap-y-4.sm\\:gap-y-6 > div.relative.shrink-0.transform-gpu.will-change-\\[height\\,min-height\\].z-50 > div:nth-child(2) > div > div.short\\:text-sm.flex.flex-col.items-center > span"
            ));
            String finalScore = scoreElement.getText();
            System.out.println("最终得分：" + finalScore.split(" ")[0]);
        } catch (Exception e) {
            System.out.println("无法获取最终得分：" + e.getMessage());
        }
    }
}
