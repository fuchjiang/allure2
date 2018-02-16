package io.qameta.allure.tree;

import io.qameta.allure.entity.TestParameter;
import io.qameta.allure.entity.TestResult;
import io.qameta.allure.entity.TestStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author charlie (Dmitry Baev).
 */
@Data
@Accessors(chain = true)
public class TestResultLeafNode implements LeafNode {

    private final Long id;

    private final String name;

    private final String parentUid;

    private final TestStatus status;

    private final Long start;

    private final Long stop;

    private final Long duration;

    private final boolean flaky;

    private final List<String> parameters;

    public TestResultLeafNode(final String parentUid, final TestResult testResult) {
        this(
                parentUid,
                testResult.getName(),
                testResult.getId(),
                testResult.getStatus(),
                testResult.getStart(),
                testResult.getStop(),
                testResult.getDuration(),
                testResult.isFlaky(),
                testResult.getParameters().stream()
                        .filter(TestParameter::isNotHidden)
                        .map(TestParameter::getValue)
                        .collect(Collectors.toList())
        );
    }

    @SuppressWarnings("ParameterNumber")
    public TestResultLeafNode(final String parentUid, final String name, final Long id,
                              final TestStatus status, final Long start, final Long stop, final Long duration,
                              final boolean flaky, final List<String> parameters) {
        this.name = name;
        this.parentUid = parentUid;
        this.id = id;
        this.status = status;
        this.start = start;
        this.stop = stop;
        this.duration = duration;
        this.flaky = flaky;
        this.parameters = parameters;
    }
}