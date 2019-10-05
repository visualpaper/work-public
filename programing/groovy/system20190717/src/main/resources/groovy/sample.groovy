package groovy

import java.util.function.Function

sample {
	hello "aaaa", { String value -> "bbb" + value } as Function
}
