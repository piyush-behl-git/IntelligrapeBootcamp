package com.ig.bc

import com.ig.bc.dto.ResourceDTO
import com.ig.bc.enums.Visibility

class Topic {
    String name
    Visibility visibility
    static belongsTo = [owner: User]
    static hasMany = [subscriptions: Subscription, resources: Resource]
    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }

    static final MAX_RESULT_COUNT = 10

    List<ResourceDTO> getMostReadResources() {
        List<Object[]> resourcesAndReadCounts = resourceAndReadCountCriteria()
        List<ResourceDTO> mostReadResources = []
        extractMostReadResources(resourcesAndReadCounts, mostReadResources)
        return mostReadResources
    }

    private void extractMostReadResources(List<Object[]> resourcesAndReadCounts, List<ResourceDTO> mostReadResources) {
        resourcesAndReadCounts.each { resourceAndReadCount ->
            mostReadResources << new ResourceDTO(resource: resourceAndReadCount.first(), readCount: resourceAndReadCount.last())
        }
    }

    private List<Object[]> resourceAndReadCountCriteria() {
        List resourcesAndReadCounts = ReadingItem.createCriteria().list {
            projections {
                groupProperty('resource')
                count('resource', 'r')
            }
            inList('resource', this.resources)
            maxResults MAX_RESULT_COUNT
            order('r', 'desc')
        }
        resourcesAndReadCounts
    }

    String toString() {
        name
    }
}
