package it.rpo.aes.handlers;

import org.alfresco.event.sdk.handling.filter.EventFilter;
import org.alfresco.event.sdk.handling.filter.IsFileFilter;
import org.alfresco.event.sdk.handling.handler.OnNodeCreatedEventHandler;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.NodeResource;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NodeCreationHandler implements OnNodeCreatedEventHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(NodeCreationHandler.class);

	@Override
	public void handleEvent(RepoEvent<DataAttributes<Resource>> event) {

		final NodeResource nodeResource = (NodeResource) event.getData().getResource();
		final String name = nodeResource.getName();
		final String nodeType = nodeResource.getNodeType();
		final String nodeCreationDate = nodeResource.getCreatedAt().toString();
		final String nodeCreator = nodeResource.getCreatedByUser().getDisplayName();
		
		logger.info("Node {} of type {} created at {} by {}", name, nodeType, nodeCreationDate, nodeCreator);
		
		
	}
	
    public EventFilter getEventFilter() {
        return IsFileFilter.get();
    }

}
