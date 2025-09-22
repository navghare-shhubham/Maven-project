@Test
public void verifyPunchInToast() {
    driver.findElement(By.id("punchInBtn")).click();

    // Toast appears
    String toastMessage = driver.findElement(By.cssSelector(".toast-message")).getText();

    Assert.assertTrue(toastMessage.contains("Punch In Successful"),
            "Expected toast message not displayed!");
}
