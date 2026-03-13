FROM eclipse-temurin:21-alpine

  # Dossier de travail dans le conteneur
WORKDIR /app

# On copie le fichier JAR généré par l'étape Maven Build du workflow
COPY target/*.jar app.jar

EXPOSE 8080
  
  # Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]