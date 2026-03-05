# Laboratorio BDD con Selenium + Java

Este repositorio documenta un laboratorio de **BDD (Behavior Driven Development)** usando **Java, Maven, Cucumber y Selenium** dentro de un entorno de contenedores (Dev Container/Codespaces).

## Autor

- **Nombre:** Cristian Camilo Gómez Fernández

## ¿Qué se hizo en el laboratorio?

Se siguió el flujo clásico del laboratorio:

1. Crear proyecto Maven base.
2. Agregar dependencias de Selenium + Cucumber.
3. Definir estructura BDD (`features`, `steps`, `runners`).
4. Implementar escenario inicial de búsqueda.
5. Ejecutar pruebas con `mvn test` y revisar reportes.

## Dependencias principales

- `org.seleniumhq.selenium:selenium-java:4.0.0`
- `io.cucumber:cucumber-java:7.0.0`
- `io.cucumber:cucumber-junit:7.0.0`
- `junit:junit:4.13.2`

## Escenarios implementados

### 1) Escenario base del laboratorio

Escenario de búsqueda para validar el flujo end-to-end con Cucumber + Selenium.

Archivo feature:
- `bdd-java/src/test/java/features/google_search.feature`

Steps principales:
- abrir buscador
- escribir término
- validar resultado esperado

### 2) Escenario adicional (extra al laboratorio)

Se agregó un escenario nuevo en Wikipedia para practicar BDD con interacción de UI más específica:

- **cambiar tamaño de texto** en el panel de Apariencia (opción "Grande")

Archivo feature:
- `bdd-java/src/test/java/features/wikipedia_text_size_filter.feature`

Steps dedicados (archivo separado):
- `bdd-java/src/test/java/steps/WikipediaAppearanceSteps.java`

## ¿Cómo ejecutar?

Desde la carpeta del proyecto Maven:

```bash
cd bdd-java
mvn test
```

## Reportes

Después de ejecutar, Cucumber/Surefire generan reportes en `target/`, por ejemplo:

- `bdd-java/target/HtmlReports/report.html`
- `bdd-java/target/surefire-reports/`

Resultado de referencia del reporte:

![Resultado del reporte BDD](https://i.ibb.co/MDp4Pjc3/image.png)

## Experiencia trabajando con BDD (reflexión)

La experiencia fue muy útil para entender que BDD no es solo "escribir Given/When/Then", sino diseñar escenarios claros y mantenibles.

Lo más valioso fue:

- separar bien la intención del negocio en Gherkin,
- mover la complejidad técnica a los step definitions,
- y aprender que en automatización web hay factores reales de inestabilidad (captchas, cambios de DOM, diferencias entre headless y no-headless).

El escenario adicional de Wikipedia ayudó mucho porque obligó a trabajar una interacción de UI más precisa (no solo búsqueda), dejando un ejemplo más cercano a pruebas funcionales reales.
