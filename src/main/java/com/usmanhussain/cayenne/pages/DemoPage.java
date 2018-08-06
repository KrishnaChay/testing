package com.usmanhussain.cayenne.pages;

import com.usmanhussain.habanero.framework.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DemoPage extends AbstractPage {


    public WebElement testElement() {
        return waitAndFindElement(By.id("test"));

    }
}
