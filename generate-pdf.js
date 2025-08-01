const puppeteer = require('puppeteer');

(async () => {
    const browser = await puppeteer.launch({
        headless: true,
        args: ['--no-sandbox', '--disable-setuid-sandbox']
    });
    const page = await browser.newPage();
    await page.goto('http://localhost:8888', { waitUntil: 'networkidle0' });
    await new Promise(resolve => setTimeout(resolve, 4000));
    await page.pdf({
        path: 'build/allure-report/allure-report.pdf',
        width: '14in',
        height: '8.5in',
        printBackground: true,
        margin: { top: '20px', bottom: '20px', left: '20px', right: '20px' }
    });
    await browser.close();
    console.log('PDF generado exitosamente.');
})();
