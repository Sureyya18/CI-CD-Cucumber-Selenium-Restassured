pipeline {
    agent any

    parameters {
        choice(
            name: 'TEST_TAG',
            choices: ['@wip', '@smoke', '@regression', '@sanity', '@all'],
            description: 'Wahlen Sie Tag aus!'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Wahlen Sie Browser aus'
        )
        string(
            name: 'THREAD_COUNT',
            defaultValue: '3',
            description: 'Parallel test thread Anzahl'
        )
    }

    environment {
        MAVEN_HOME = 'java -jar jenkins.war --enable-future-java --httpPort=8080'
        JAVA_HOME = 'C:\Program Files\Java\jdk-24'
        PATH = "${MAVEN_HOME}/bin;${JAVA_HOME}/bin;${PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    echo "Projekt wird geklont..."
                    checkout scm
                }
            }
        }

        stage('Setup') {
            steps {
                script {
                    echo "Umgebungsvariablen werden konfiguriert..."
                    bat '''
                        echo Browser: %BROWSER%
                        echo Test Tag: %TEST_TAG%
                        echo Thread Count: %THREAD_COUNT%
                    '''
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo "Maven Build wird ausgefuehrt..."
                    bat '''
                        mvn clean compile
                    '''
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    echo "Cucumber Tests werden ausgefuehrt..."
                    bat '''
                        mvn test -Dcucumber.filter.tags="%TEST_TAG%" -Dbrowser="%BROWSER%" -Dthreads="%THREAD_COUNT%"
                    '''
                }
            }
        }

        stage('Rerun Failed Tests') {
            steps {
                script {
                    echo "Fehlgeschlagene Tests werden ueberprueft..."
                    def fileExists = bat(
                        script: 'if exist target\\rerun.txt (exit 0) else (exit 1)',
                        returnStatus: true
                    ) == 0

                    if (fileExists) {
                        echo "Fehlgeschlagene Tests gefunden, werden erneut ausgefuehrt..."
                        bat '''
                            mvn test -Dcucumber.filter.tags=@rerun -Dbrowser="%BROWSER%"
                        ''' ?: true
                    } else {
                        echo "Alle Tests erfolgreich, Neustart nicht erforderlich."
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                echo "Berichte werden verarbeitet..."

                // Cucumber HTML Bericht veroeffentlichen
                publishHTML([
                    reportDir: 'target',
                    reportFiles: 'html-reports.html',
                    reportName: 'Cucumber HTML Bericht',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])

                // Testergebnisse archivieren
                archiveArtifacts(
                    artifacts: 'target/**/*.html, target/**/*.xml, target/rerun.txt',
                    allowEmptyArchive: true,
                    fingerprint: true
                )

                // JUnit Ergebnisse veroeffentlichen
                junit(
                    testResults: 'target/surefire-reports/*.xml',
                    allowEmptyResults: true,
                    keepLongStdio: true
                )
            }
        }

        success {
            script {
                echo "Tests erfolgreich abgeschlossen!"
            }
        }

        failure {
            script {
                echo "Tests fehlgeschlagen!"
                emailext(
                    subject: "Jenkins Build Fehler: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                        Build fehlgeschlagen.

                        Job: ${env.JOB_NAME}
                        Build Nummer: ${env.BUILD_NUMBER}
                        Build URL: ${env.BUILD_URL}

                        Berichte: ${env.BUILD_URL}Cucumber_HTML_Report/
                    """,
                    to: 'cankirili_18@hotmail.de',
                    recipientProviders: [developers(), requestor()]
                )
            }
        }

        unstable {
            script {
                echo "Einige Tests sind fehlgeschlagen!"
            }
        }

        cleanup {
            script {
                echo "Bereinigung wird ausgefuehrt..."
                cleanWs()
            }
        }
    }
}