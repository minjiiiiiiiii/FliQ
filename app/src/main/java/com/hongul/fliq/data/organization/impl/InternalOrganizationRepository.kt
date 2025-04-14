package com.hongul.fliq.data.organization.impl

import com.hongul.fliq.data.organization.OrganizationRepository

class InternalOrganizationRepository(
    private val dao: OrganizationRepository
): OrganizationRepository {
}