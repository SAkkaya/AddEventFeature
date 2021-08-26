package com.translantik.pages;

import com.translantik.utilities.BrowserUtils;
import com.translantik.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class FleetVehiclePage extends BasePage {
    int expectedSize;
    int actualSize;
    List<WebElement> checkBoxes;

    @FindBy(css = "a.add-filter-button")
    public WebElement manageFilters;

    @FindBy(xpath = "//a[@title='Filters']")
    public WebElement filterButton;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement manageFilterSearchBox;

    @FindBy(xpath = "//div[@class='value-field-frame']/input[@name='value']")
    public WebElement containsSearchBox;

    @FindBy(xpath = "//tbody[@class='grid-body']/tr[1]/td[3]")
    public WebElement driverColumnName;

    @FindBy(xpath = "//tbody[@class='grid-body']/tr[1]/td[2]")
    public WebElement tagName;

    @FindBy(xpath = "//input[@id='s2id_autogen2']")
    public WebElement isAnyOfSearchBox;

    @FindBy(xpath = "//li/label/input")
    public List<WebElement> manageFilterCheckBoxes;

    @FindBy(xpath = "//div[@class='filter-item oro-drop']")
    public List<WebElement> filteredItems;

    @FindBy(xpath = "//a[@class='action btn reset-action mode-text-only']")
    public WebElement resetButton;

    @FindBy(css = " tbody tr")
    public WebElement firstRowAfterFiltering;

    @FindBy(xpath = "(//tbody/tr)[1]")
    public WebElement firstRowCar;


    public WebElement getdataColumns(String dataColumns){
        String xpath = "//input[@title='"+ dataColumns +"']";
        return Driver.get().findElement(By.xpath(xpath));
    }

    public WebElement getColumnsFilterAll(String dataColumns){
        String xpath = "//div[contains(text(),'"+ dataColumns +"')]";
        return Driver.get().findElement(By.xpath(xpath));
    }

    public WebElement getActualCellName(String dataColumns){
        String xpath = "//td[@data-column-label='"+ dataColumns +"']";
        return Driver.get().findElement(By.xpath(xpath));
    }


    public void methodFilter(String dataColumns) {
        new FleetVehiclePage().manageFilterSearchBox.sendKeys(dataColumns);
        new FleetVehiclePage().getdataColumns(dataColumns).click();
        new FleetVehiclePage().getColumnsFilterAll(dataColumns).click();

    }


    @When("select all boxes")
    public void select_all_boxes() {


        BrowserUtils.waitFor(3);
        checkBoxes = new FleetVehiclePage().manageFilterCheckBoxes;
        expectedSize = checkBoxes.size();
//        for (WebElement checkBox : checkBoxes) {
//            checkBox.click();
//        }
        for (int i = 0; i <checkBoxes.size() ; i++) {
            checkBoxes.get(i).click();
            new FleetVehiclePage().waitUntilLoaderScreenDisappear();

        }

    }
    @Then("all check boxes are selected")
    public void all_check_boxes_are_selected() {
        List<WebElement> filteredItems = new FleetVehiclePage().filteredItems;
        actualSize = filteredItems.size();
        Assert.assertEquals(expectedSize,actualSize);

    }
    @When("the user click on reset button")
    public void the_user_click_on_reset_button() {
        new FleetVehiclePage().resetButton.click();
    }
    @Then("User can  remove all filterings by using the reset button")
    public void user_can_remove_all_filterings_by_using_the_reset_button() {
        for (WebElement checkBox : checkBoxes) {
            Assert.assertFalse(checkBox.isSelected());
        }
    }
    @FindBy(tagName = "table")
    public WebElement table;

    @FindBy(xpath = "//div/a/i[@class='fa-upload']")
    public WebElement exportGrid;

    @FindBy(xpath = "//li/a[@title='CSV']")
    public WebElement csv;

    @FindBy(xpath = "//li/a[@title='XLSX']")
    public WebElement xlsx;

    @FindBy(xpath = "//div[@class='message']")
    public WebElement message;

    @FindBy(className = "input-widget")
    public WebElement pageNum;

    @FindBy(xpath = "//label[contains(text(),'of ')][1]")
    public WebElement totalPages;

    @FindBy(xpath = "//label[contains(text(),'Total of')]")
    public WebElement totalRecordings;

    @FindBy(xpath = "//a[.='...']")
    public WebElement threeDot;
    //i[@class='fa-trash-o hide-text']
    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menuaction-cell launchers-dropdown-menu detach dropdown-menufloating']//i[@class='fa-trash-o hide-text']")
    public WebElement deleteButton;

    @FindBy(css = ".ok")
    public WebElement confirmDelete;

    @FindBy(xpath = "//div[@class='message']")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//tbody/tr/td[2]")
    public WebElement firstCarOnTable;

    @FindBy(css = ".remove-button")
    public WebElement removeButton;





    @FindBy(xpath = "(//button[@data-toggle='dropdown'])[1]")
    public WebElement subFilter;

    @FindBy(css = "[name='value']")
    public WebElement subFilterBox1;
    @FindBy(css = "[name='value_end']")
    public WebElement subFilterBox2;
    @FindBy(css = ".btn.btn-primary.filter-update")
    public WebElement updateButton;

    public WebElement getSubFilterType(String string){
        WebElement element=Driver.get().findElement(By.xpath("//a[text()='"+string+"']"));
        return element;
    }

    public List<Integer> getColumnValues(String string){
        List<WebElement> elements =Driver.get().findElements(By.cssSelector("td[data-column-label='"+string+"']"));
        List<Integer> values=new ArrayList<>();
        for (WebElement element : elements) {
            values.add(Integer.parseInt(element.getText()));
        }
        return values;
    }


}



