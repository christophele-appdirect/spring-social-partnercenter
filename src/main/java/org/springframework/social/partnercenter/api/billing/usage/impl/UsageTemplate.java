package org.springframework.social.partnercenter.api.billing.usage.impl;

import static java.time.ZoneId.of;
import static org.springframework.social.partnercenter.api.billing.usage.Granularity.DAILY;
import static org.springframework.social.partnercenter.api.validation.Assertion.notNull;
import static org.springframework.social.partnercenter.time.PartnerCenterDateTimeFormatter.PARTNER_CENTER_UTC;

import java.time.Instant;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.social.partnercenter.PartnerCenter;
import org.springframework.social.partnercenter.api.PagingResourceTemplate;
import org.springframework.social.partnercenter.api.PartnerCenterResponse;
import org.springframework.social.partnercenter.api.billing.usage.AzureResourceMonthlyUsageRecord;
import org.springframework.social.partnercenter.api.billing.usage.CustomerUsageSummary;
import org.springframework.social.partnercenter.api.billing.usage.Granularity;
import org.springframework.social.partnercenter.api.billing.usage.PartnerUsageSummary;
import org.springframework.social.partnercenter.api.billing.usage.SubscriptionDailyUsage;
import org.springframework.social.partnercenter.api.billing.usage.SubscriptionMonthlyUsageRecord;
import org.springframework.social.partnercenter.api.billing.usage.SubscriptionUsageSummary;
import org.springframework.social.partnercenter.api.billing.usage.UsageOperations;
import org.springframework.social.partnercenter.api.billing.usage.UtilizationRecord;
import org.springframework.social.partnercenter.api.uri.UriProvider;
import org.springframework.social.partnercenter.http.client.RestResource;

public class UsageTemplate extends PagingResourceTemplate<UtilizationRecord> implements UsageOperations {
	private static final String SUBSCRIPTIONS = "subscriptions";
	private static final String USAGE_SUMMARY = "usagesummary";
	private static final String USAGE_RECORDS = "usagerecords";
	private RestResource restResource;
	private UriProvider uriProvider;

	public UsageTemplate(UriProvider uriProvider, RestResource restResource, boolean isAuthorized) {
		super(restResource, isAuthorized, new ParameterizedTypeReference<PartnerCenterResponse<UtilizationRecord>>() {});
		this.restResource = restResource;
		this.uriProvider = uriProvider;
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(startDateTime, "startDateTime");
		notNull(endDateTime, "endDateTime");
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, DAILY, true, 1000);
	}
	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(startDateTime, "startDateTime");
		notNull(endDateTime, "endDateTime");
		notNull(granularity, "granularity");
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, granularity, true, 1000);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, Boolean showDetails) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(startDateTime, "startDateTime");
		notNull(endDateTime, "endDateTime");
		notNull(granularity, "granularity");
		notNull(showDetails, "showDetails");
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, granularity, showDetails, 1000);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, Boolean showDetails, Integer size) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(startDateTime, "startDateTime");
		notNull(endDateTime, "endDateTime");
		notNull(granularity, "granularity");
		notNull(showDetails, "showDetails");
		notNull(size, "size");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, "utilizations", "azure")
				.queryParam("start_time", startDateTime.atZone(of("UTC")).format(PARTNER_CENTER_UTC))
				.queryParam("end_time", endDateTime.atZone(of("UTC")).format(PARTNER_CENTER_UTC))
				.queryParam("granularity", granularity.jsonValue())
				.queryParam("show_details", showDetails)
				.queryParam("size", size)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<UtilizationRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Granularity granularity, Integer size) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(startDateTime, "startDateTime");
		notNull(endDateTime, "endDateTime");
		notNull(granularity, "granularity");
		notNull(size, "size");
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, granularity, true, size);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Boolean showDetails, Integer size) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(startDateTime, "startDateTime");
		notNull(endDateTime, "endDateTime");
		notNull(size, "size");
		notNull(showDetails, "showDetails");
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, DAILY, showDetails, size);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Boolean showDetails) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(startDateTime, "startDateTime");
		notNull(endDateTime, "endDateTime");
		notNull(showDetails, "showDetails");
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, DAILY, showDetails, 1000);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<UtilizationRecord>> getUtilizationRecords(String customerId, String subscriptionId, Instant startDateTime, Instant endDateTime, Integer size) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		notNull(startDateTime, "startDateTime");
		notNull(endDateTime, "endDateTime");
		notNull(size, "size");
		return getUtilizationRecords(customerId, subscriptionId, startDateTime, endDateTime, DAILY, true, size);
	}

	@Override
	public ResponseEntity<CustomerUsageSummary> getUsageSummary(String customerId) {
		notNull(customerId, "customerId");
		return restResource.request()
				.pathSegment(customerId, USAGE_SUMMARY)
				.get(CustomerUsageSummary.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<AzureResourceMonthlyUsageRecord>> getSubscriptionResourceUsageInformation(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, USAGE_RECORDS, "resources")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<AzureResourceMonthlyUsageRecord>>() {});
	}

	@Override
	public ResponseEntity<SubscriptionUsageSummary> getSubscriptionUsageSummary(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, USAGE_SUMMARY)
				.get(SubscriptionUsageSummary.class);
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<SubscriptionDailyUsage>> getDailySubscriptionUsage(String customerId, String subscriptionId) {
		notNull(customerId, "customerId");
		notNull(subscriptionId, "subscriptionId");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, subscriptionId, USAGE_RECORDS, "daily")
				.get(new ParameterizedTypeReference<PartnerCenterResponse<SubscriptionDailyUsage>>() {});
	}

	@Override
	public ResponseEntity<PartnerCenterResponse<SubscriptionMonthlyUsageRecord>> getMonthlyUsageForSubscriptions(String customerId) {
		notNull(customerId, "customerId");
		return restResource.request()
				.pathSegment(customerId, SUBSCRIPTIONS, USAGE_RECORDS)
				.get(new ParameterizedTypeReference<PartnerCenterResponse<SubscriptionMonthlyUsageRecord>>() {});
	}

	@Override
	public ResponseEntity<PartnerUsageSummary> getPartnerUsage() {
		return restResource.request(uriProvider.partnerBaseUri().build().toUri())
				.pathSegment(USAGE_SUMMARY)
				.get(PartnerUsageSummary.class);
	}

	@Override
	protected String getProviderId() {
		return PartnerCenter.PROVIDER_ID;
	}

}
