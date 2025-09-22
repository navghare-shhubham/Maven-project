@Test(dataProvider = "customerData")
public void addCustomer(String custName, String custEmail) {
    driver.findElement(By.id("addCustomerBtn")).click();

    driver.findElement(By.id("custName")).sendKeys(custName);
    driver.findElement(By.id("custEmail")).sendKeys(custEmail);
    driver.findElement(By.id("saveBtn")).click();

    // Validate success message
    String msg = driver.findElement(By.cssSelector(".toast-message")).getText();
    Assert.assertTrue(msg.contains("Customer added"),
            "Customer addition failed for: " + custName);
}

@DataProvider(name = "customerData")
public Object[][] customerData() {
    return new Object[][]{
            {"Alice", "alice@test.com"},
            {"Bob", "bob@test.com"}
    };
}
