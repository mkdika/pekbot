package com.kmklabs.pekbotserver.model

import java.util.HashMap
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("kind", "id", "created_at", "updated_at", "accepted_at", "story_type", "name", "current_state", "requested_by_id", "url", "project_id", "owner_ids", "labels", "owned_by_id")
class StoryResponse {

    @field:JsonProperty("kind")
    var kind: String? = null
    @field:JsonProperty("id")
    var id: Int = 0
    @field:JsonProperty("created_at")
    var createdAt: String? = null
    @field:JsonProperty("updated_at")
    var updatedAt: String? = null
    @field:JsonProperty("accepted_at")
    var acceptedAt: String? = null
    @field:JsonProperty("story_type")
    var storyType: String? = null
    @field:JsonProperty("name")
    var name: String? = null
    @field:JsonProperty("current_state")
    var currentState: String? = null
    @field:JsonProperty("requested_by_id")
    var requestedById: Int = 0
    @field:JsonProperty("url")
    var url: String? = null
    @field:JsonProperty("project_id")
    var projectId: Int = 0
    @field:JsonProperty("owner_ids")
    var ownerIds: List<Int>? = null
    @field:JsonProperty("labels")
    var labels: List<Label>? = null
    @field:JsonProperty("owned_by_id")
    var ownedById: Int = 0
    @field:JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }

}
