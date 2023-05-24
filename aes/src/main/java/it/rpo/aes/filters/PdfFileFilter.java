package it.rpo.aes.filters;

import org.alfresco.event.sdk.handling.filter.AbstractEventFilter;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.NodeResource;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfFileFilter extends AbstractEventFilter {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(PdfFileFilter.class);

		
    private PdfFileFilter() {

    }
    
    public static PdfFileFilter get() {
        return new PdfFileFilter();
    }

    @Override
    public boolean test(final RepoEvent<DataAttributes<Resource>> event) {
        LOGGER.debug("Checking filter for mimeType application/pdf and event {}", event);
        return isContentEvent(event) && ((NodeResource)event.getData().getResource()).getContent().getMimeType().equals("application/pdf");
    }
}