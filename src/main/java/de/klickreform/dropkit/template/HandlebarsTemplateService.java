package de.klickreform.dropkit.template;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.FieldValueResolver;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.IOException;

/**
 * TemplateService to resolve Handlebars Templates.
 *
 * @author Benjamin Bestmann
 */
public class HandlebarsTemplateService implements TemplateService {

    private Handlebars handlebars;
    private TemplateLoader loader;

    public HandlebarsTemplateService(String path, String fileType) {
        loader = new ClassPathTemplateLoader();
        loader.setPrefix(path);
        loader.setSuffix(fileType);
        this.handlebars = new Handlebars(loader);
    }

    public String resolve(String template, Object model) throws IOException {
        // Compile Template and Apply
        Template compiledTemplate = handlebars.compile(template);
        Context context = Context.newBuilder(model).resolver(FieldValueResolver.INSTANCE).build();
        return compiledTemplate.apply(context);
    }

}
