package it.rpo.aes.handlers;

import org.alfresco.event.sdk.handling.filter.AspectAddedFilter;
import org.alfresco.event.sdk.handling.filter.EventFilter;
import org.alfresco.event.sdk.handling.filter.PropertyChangedFilter;
import org.alfresco.event.sdk.handling.handler.OnNodeUpdatedEventHandler;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NodeUpdateHandler implements OnNodeUpdatedEventHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(NodeUpdateHandler.class);
	
	@Override
	public void handleEvent(RepoEvent<DataAttributes<Resource>> event) {
		
		logger.info("Node's title has been modified");
		// Do your magic here
		
	}
	
    public EventFilter getEventFilter() {
        return PropertyChangedFilter.of("cm:title")
        		.or(AspectAddedFilter.of("cm:titled"));
    }

}
