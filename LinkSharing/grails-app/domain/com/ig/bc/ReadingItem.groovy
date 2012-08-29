package com.ig.bc

class ReadingItem {
    boolean isFavorite
    boolean isRead
    static belongsTo = [user: User, resource: Resource]
    static constraints = {
    }

    String toString() {
        return "${user}_${resource}"
    }
}
