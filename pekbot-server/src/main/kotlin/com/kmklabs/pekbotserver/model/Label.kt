package com.kmklabs.pekbotserver.model

import java.util.HashMap
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("id", "project_id", "kind", "name", "created_at", "updated_at")
class Label {
    
    @field:JsonProperty("id")
    var id: Int = 0
    @field:JsonProperty("project_id")
    var projectId: Int = 0
    @field:JsonProperty("kind")
    var kind: String? = null
    @field:JsonProperty("name")
    var name: String? = null
    @field:JsonProperty("created_at")
    var createdAt: String? = null
    @field:JsonProperty("updated_at")
    var updatedAt: String? = null
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
