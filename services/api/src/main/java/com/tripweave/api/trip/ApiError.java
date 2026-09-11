package com.tripweave.api.trip;

import java.util.Map;

record ApiError(String message, Map<String, String> fieldErrors) {}
