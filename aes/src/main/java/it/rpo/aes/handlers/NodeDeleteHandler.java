package it.rpo.aes.handlers;

import org.alfresco.event.sdk.handling.filter.EventFilter;
import org.alfresco.event.sdk.handling.filter.IsFileFilter;
import org.alfresco.event.sdk.handling.handler.OnNodeDeletedEventHandler;
import org.alfresco.event.sdk.model.v1.model.DataAttributes;
import org.alfresco.event.sdk.model.v1.model.NodeResource;
import org.alfresco.event.sdk.model.v1.model.RepoEvent;
import org.alfresco.event.sdk.model.v1.model.Resource;
import org.alfresco.search.handler.SearchApi;
import org.alfresco.search.model.RequestQuery;
import org.alfresco.search.model.ResultSetPaging;
import org.alfresco.search.model.SearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import it.rpo.aes.filters.PdfFileFilter;

@Component
public class NodeDeleteHandler implements OnNodeDeletedEventHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(NodeDeleteHandler.class);
	
	@Autowired
	SearchApi searchApi;
	
	@Override
	public void handleEvent(RepoEvent<DataAttributes<Resource>> event) {
		
		final NodeResource nodeResource = (NodeResource) event.getData().getResource();
		final String name = nodeResource.getName();
		final String type = nodeResource.getNodeType();
		
		logger.info("Node {} of type {} has been deleted. Searching for other files with same name and same type..", name, type);
		
		ResponseEntity<ResultSetPaging> result = searchApi.search(new SearchRequest()
                .query(new RequestQuery()
                        .language(RequestQuery.LanguageEnum.AFTS)
                        .query("(cm:name:\"" + name + "\" AND TYPE:\"" + type + "\")")));
				
		if (result.hasBody()) {
			logger.info("There are {} nodes with these attributes in the repository", result.getBody().getList().getPagination().getCount());
		}
		
	}
	
	
    public EventFilter getEventFilter() {
        return IsFileFilter.get()
        		.and(PdfFileFilter.get());
    }
    
}