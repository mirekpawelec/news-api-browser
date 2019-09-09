#NEWS-API-BROWSER, statements to run app:
- clone the git repository
    git clone https://github.com/mirekpawelec/news-browser.git
- move to the project directory
    cd news-browser
- default branch is the develop, if would be other, please switch to the mentioned one
    git checkout remotes/origin/develop
    git checkout develop
- commands to build and run the project
    mvn clean install && java -jar news-browser-standalone/target/news-browser-standalone-0.0.1-SNAPSHOT.war
- enter the path into browser
    http://localhost:8001/
