package utilites;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testbase.baseclass;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Extentreportmanager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext) {
        // Create report file name with timestamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Create spark reporter instance
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        // Report Configuration
        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("Opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        // Attach reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add environment details
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new baseclass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

        // ===== Open the report automatically in Chrome =====
        try {
            File htmlFile = new File(".\\reports\\" + repName);

            if (htmlFile.exists()) {
                // Path to Chrome executable (adjust if needed)
                String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

                // Launch Chrome with the report
                Runtime.getRuntime().exec(new String[]{chromePath, htmlFile.getAbsolutePath()});

                System.out.println("Report opened in Chrome: " + htmlFile.getAbsolutePath());
            } else {
                System.out.println("Report file not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ===== Email Functionality (Commented for now) =====
        /*
        sendEmailWithReport(repName);
        */
    }

    /*
    // ================= Email Sending Method =================
    // Uncomment and configure if you want to send report via email
    public void sendEmailWithReport(String repName) {
        try {
            String reportPath = System.getProperty("user.dir") + "\\reports\\" + repName;

            // Create the attachment
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(reportPath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Extent Report");
            attachment.setName(repName);

            // Create the email message
            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("your-email@gmail.com", "your-app-password")); // Use app password
            email.setSSLOnConnect(true);

            email.setFrom("your-email@gmail.com"); // Sender
            email.setSubject("Automation Test Report - Extent");
            email.setMsg("Hi,\n\nPlease find the attached automation test report.\n\nRegards,\nAutomation Team");
            email.addTo("recipient@example.com"); // Receiver

            email.attach(attachment); // Attach report
            email.send();

            System.out.println("Test report emailed successfully!");

        } catch (EmailException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }
    */
}
