package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.PizzaSuggestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-22T18:16:30.929Z[GMT]")
@Controller
public class SuggestionsApiController implements SuggestionsApi {

    private static final Logger log = LoggerFactory.getLogger(SuggestionsApiController.class);
    private final HttpServletRequest request;

    private final String HEADER_VALUE = "Accept";
    private final String HEADER_CONTENTS = "application/json";

    private static final int SLICES_PER_ADULT = 3;
    private static final int SLICES_PER_CHILD = 2;
    private static final int SLICES_PER_SMALL = 6;
    private static final int SLICES_PER_MEDIUM = 8;
    private static final int SLICES_PER_LARGE = 10;

    private final String SIZE_ONE_DESCRIPTION = "small";
    private final String SIZE_TWO_DESCRIPTION = "medium";
    private final String SIZE_THREE_DESCRIPTION = "large";

    @org.springframework.beans.factory.annotation.Autowired
    public SuggestionsApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity<PizzaSuggestion> getNumberOfPizzas(
            @NotNull @ApiParam(value = "Number of adults", required = true)
            @Valid @RequestParam(value = "adults", required = true) Integer adults,
            @NotNull @ApiParam(value = "Number of children", required = true)
            @Valid @RequestParam(value = "children", required = true) Integer children,
            @ApiParam(value = "The preferred size, if given all suggested pizzas will be this size. Must be a valid size (small, medium, large).")
            @Valid @RequestParam(value = "preferredSize", required = false) String preferredSize) {

        if (children < 0 | adults < 0) {
            return new ResponseEntity<PizzaSuggestion>(HttpStatus.BAD_REQUEST);
        }

        String accept = request.getHeader(HEADER_VALUE);
        if (accept != null && accept.contains(HEADER_CONTENTS)) {
            try {
                PizzaSuggestion suggestion;
                if (preferredSize == null) {
                    //request paramater is not specified
                    suggestion = getSuggestion(adults, children);
                } else {
                    if (preferredSize == null) {
                        return new ResponseEntity<PizzaSuggestion>(HttpStatus.BAD_REQUEST);
                    }
                    suggestion = getSuggestion(preferredSize, adults, children);
                    if (suggestion == null) {
                        return new ResponseEntity<PizzaSuggestion>(HttpStatus.BAD_REQUEST);
                    }
                }
                return new ResponseEntity<PizzaSuggestion>(suggestion, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Internal Server Error", e);
                return new ResponseEntity<PizzaSuggestion>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<PizzaSuggestion>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Creates a PiazzaSuggestion with no preferred size. Priortizes larger pizzas first
     *
     * @param adults   the number of adults
     * @param children the number of children
     */
    private PizzaSuggestion getSuggestion(Integer adults, Integer children) {
        Integer totalSlices = (adults * SLICES_PER_ADULT) + (children * SLICES_PER_CHILD);
        int small = 0;
        int medium = 0;
        int large = 0;
        large = totalSlices / SLICES_PER_LARGE;
        int remainder = totalSlices % SLICES_PER_LARGE;
        medium = remainder / SLICES_PER_MEDIUM;
        remainder = remainder % SLICES_PER_MEDIUM;
        small = remainder / SLICES_PER_SMALL;
        remainder = remainder % SLICES_PER_SMALL;
        if (remainder > 0) {
            small++;
        }
        PizzaSuggestion suggestion = new PizzaSuggestion();
        suggestion.setSmall(small);
        suggestion.setMedium(medium);
        suggestion.setLarge(large);
        return suggestion;
    }

    /**
     * Gets a suggestion for the number of pizzas for a group. This returns all pizzas of the size get
     * given by preferredSize.
     *
     * @param preferredSize The size all pizzas in the suggestion should be
     * @param adults        The number of adults
     * @param children      The numbe of childre
     * @return A pizza suggestion object containing the order reccomendation
     */
    private PizzaSuggestion getSuggestion(String preferredSize, Integer adults, Integer children) {
        Integer totalSlices = (adults * SLICES_PER_ADULT) + (children * SLICES_PER_CHILD);
        int small = 0;
        int medium = 0;
        int large = 0;
        switch (preferredSize.toLowerCase()) {
            case SIZE_ONE_DESCRIPTION:
                small = totalSlices / SLICES_PER_SMALL;
                if (totalSlices % SLICES_PER_SMALL != 0) {
                    small++;
                }
                break;
            case SIZE_TWO_DESCRIPTION:
                medium = totalSlices / SLICES_PER_MEDIUM;
                if (totalSlices % SLICES_PER_MEDIUM != 0) {
                    medium++;
                }
                break;
            case SIZE_THREE_DESCRIPTION:
                large = totalSlices / SLICES_PER_LARGE;
                if (totalSlices % SLICES_PER_LARGE != 0) {
                    large++;
                }
                break;
            default:
                return null;
        }
        PizzaSuggestion suggestion = new PizzaSuggestion();
        suggestion.setSmall(small);
        suggestion.setMedium(medium);
        suggestion.setLarge(large);
        return suggestion;
    }
}
