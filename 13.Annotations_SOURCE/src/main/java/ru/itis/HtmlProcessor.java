package ru.itis;

import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * @author Roman Leontev
 * 00:16 09.12.2020
 * group 11-905
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотаций HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(HtmlProcessor.class, "/");
        configuration.setDefaultEncoding("UTF-8");

        Map<String, Object> map;
        Form form;
        Template template;

        for (Element element : annotatedElements) {
            map = new HashMap<>();
            form = new Form();
            map.put("form", form);
            try {
                template = configuration.getTemplate("form.ftl");
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            // получаем полный путь для генерации html
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // User.class -> User.html
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(out.toFile()));
                HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
                form.setAction(htmlForm.action());
                form.setMethod(htmlForm.method());
                List<Input> inputs = new ArrayList<>();
                form.setInputs(inputs);
                for (Element elementIn : element.getEnclosedElements()) {
                    HtmlInput inputAnnotation = elementIn.getAnnotation(HtmlInput.class);
                    if (inputAnnotation != null) {
                        inputs.add(new Input(inputAnnotation.type(), inputAnnotation.name(), inputAnnotation.placeholder()));
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            try {
                template.process(map, writer);
                writer.close();
            } catch (TemplateException | IOException e) {
                throw new IllegalStateException(e);
            }

        }
        return true;
    }

}
