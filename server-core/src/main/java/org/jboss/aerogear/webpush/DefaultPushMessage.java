/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.webpush;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class DefaultPushMessage implements PushMessage {

    private final String id;
    private final String subscription;
    private final Optional<String> receiptSubscription;
    private final String payload;
    private final Optional<Integer> ttl;
    private final LocalDateTime createdDateTime;

    public DefaultPushMessage(final String id,
                              final String subscription,
                              final Optional<String> receiptSubscription,
                              final String payload,
                              final Optional<Integer> ttl) {
        this.id = Objects.requireNonNull(id, "id");
        this.subscription = Objects.requireNonNull(subscription, "subscription");
        this.receiptSubscription = Objects.requireNonNull(receiptSubscription, "receiptSubscription");
        this.payload = Objects.requireNonNull(payload, "payload");
        this.ttl = Objects.requireNonNull(ttl, "ttl");
        ttl.ifPresent(v -> {
            if (v < 0) {
                throw new IllegalArgumentException("TTL value must be greater than 0, current: " + v);
            }
        });
        this.createdDateTime = LocalDateTime.now();
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String subscription() {
        return subscription;
    }

    @Override
    public Optional<String> receiptSubscription() {
        return receiptSubscription;
    }

    @Override
    public String payload() {
        return payload;
    }

    @Override
    public Optional<Integer> ttl() {
        return ttl;
    }

    @Override
    public LocalDateTime createdDateTime() {
        return createdDateTime;
    }

    @Override
    public String toString() {
        return "DefaultPushMessage{" +
                "id='" + id + '\'' +
                ", subscription='" + subscription + '\'' +
                ", receiptSubscription='" + receiptSubscription + '\'' +
                ", payload='" + payload + '\'' +
                ", ttl=" + ttl +
                ", createdDateTime=" + createdDateTime +
                '}';
    }
}
