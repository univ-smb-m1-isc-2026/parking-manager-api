# Étape 1 : Utiliser une image JRE légère pour l'exécution
FROM eclipse-temurin:17-jre-alpine
  
  # Dossier de travail dans le conteneur
WORKDIR /app
  
  # Copier le fichier JAR généré par l'étape Maven du workflow
  # Note : Adapte le nom si ton JAR a un nom spécifique
COPY target/*.jar app.jar
  
  # Exposer le port (8080 par défaut pour Spring)
EXPOSE 8080
  
  # Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]