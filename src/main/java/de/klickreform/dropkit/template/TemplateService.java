package de.klickreform.dropkit.template;

import java.io.IOException;

/**
 * Created by benjamin on 15.06.15.
 */
public interface TemplateService {

    public String resolve(String template, Object model) throws IOException;

}
