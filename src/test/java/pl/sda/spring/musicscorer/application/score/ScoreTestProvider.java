package pl.sda.spring.musicscorer.application.score;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class ScoreTestProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
            Arguments.of(..., ..., ..., ...),
            Arguments.of(..., ..., ..., ...)
        );
    }
}
