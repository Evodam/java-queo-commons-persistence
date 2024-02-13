/**
 * 
 */
package com.queomedia.persistence.extra.criteria;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import com.queomedia.commons.checks.Check;
import com.queomedia.commons.exceptions.NotImplementedCaseExecption;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * The Class FilterRestrictions.
 * 
 * @author engelmann
 */
public final class FilterRestrictions {

    /** Tool classes need no restrictions. */
    private FilterRestrictions() {
    }

    /**
     * Adds the criterion it it is not null.
     * 
     * @param crit the crit
     * @param nullAllowed the null allowed criterion
     */
    public static void addNullAllowed(final CriteriaQuery<?> crit, final Predicate nullAllowed) {
        Check.notNullArgument(crit, "crit");

        if (nullAllowed != null) {
            crit.where(nullAllowed);
        }
    }

    /**
     * Yes no.
     * 
     * @param propertyName the property name
     * @param restriction the restriction
     * 
     * @return the criterion
     */
    public static Predicate yesNo(final String propertyName, final YesNoDontCare restriction,
            final CriteriaBuilder criteriaBuilder,
            final Root<?> root) {
        Check.notNullArgument(propertyName, "propertyName");
        Check.notNullArgument(restriction, "restriction");

        switch (restriction) {
        case DONT_CARE:
            return null;
        case YES:
            return criteriaBuilder.equal(root, true);
        case NO:
            return criteriaBuilder.equal(root, false);

        default:
            throw new NotImplementedCaseExecption("The case " + restriction + " is not implemented.");
        }
    }

    /**
     * Enum filter.
     *
     * @param <T> the generic type
     * @param propertyName the property name
     * @param restrictions the restrictions
     * @param criteriaBuilder the criteria builder
     * @param root the root
     * @return the criterion
     */
    protected static <T extends Enum<?>> Predicate enumFilter(final String propertyName,
            final EnumFilter<T> restrictions, final CriteriaBuilder criteriaBuilder,
            final Root<?> root) {
        Check.notNullArgument(propertyName, "propertyName");
        Check.notNullArgument(restrictions, "restrictions");

        if (!restrictions.hasRestrictions()) {
            return null;
        }

        List<T> yesList = new ArrayList<T>();
        List<T> noList = new ArrayList<T>();

        for (Regulation<T> regulation : restrictions.getAllRestrictions()) {
            switch (regulation.getRule()) {
            case DONT_CARE:
                break;
            case YES:
                yesList.add(regulation.getValue());
                break;
            case NO:
                noList.add(regulation.getValue());
                break;

            default:
                throw new NotImplementedCaseExecption("The case " + regulation.getRule() + " is not implemented.");
            }
        }

        if ((yesList.size() > 0) && (noList.size() == 0)) {
            return criteriaBuilder.in(root.get(propertyName)).value(yesList);
        }
        if ((yesList.size() == 0) && (noList.size() > 0)) {
            return criteriaBuilder.not(criteriaBuilder.in(root.get(propertyName)).value(yesList));
        }
        if ((yesList.size() > 0) && (noList.size() > 0)) {
            return criteriaBuilder.and(criteriaBuilder.in(root.get(propertyName)).value(yesList),
                    criteriaBuilder.not(criteriaBuilder
                            .in(root.get(propertyName)).value(noList)));
        }
        return null;
    }

    /**
     * Adds the yes no.
     *
     * @param crit the crit
     * @param propertyName the property name
     * @param restriction the restriction
     * @param criteriaBuilder the criteria builder
     * @param root the root
     */
    public static void addYesNo(final CriteriaQuery<?> crit, final String propertyName, final YesNoDontCare restriction,
            final CriteriaBuilder criteriaBuilder,
            final Root<?> root) {
        Check.notNullArgument(crit, "crit");
        Check.notNullArgument(propertyName, "propertyName");
        Check.notNullArgument(restriction, "restriction");

        FilterRestrictions.addNullAllowed(crit,
                FilterRestrictions.yesNo(propertyName, restriction, criteriaBuilder, root));
    }

    /**
     * Adds the enum filter.
     *
     * @param <T> the generic type
     * @param crit the crit
     * @param propertyName the property name
     * @param restrictions the restrictions
     * @param criteriaBuilder the criteria builder
     * @param root the root
     */
    public static <T extends Enum<?>> void addEnumFilter(final CriteriaQuery<?> crit, final String propertyName,
            final EnumFilter<T> restrictions, final CriteriaBuilder criteriaBuilder,
            final Root<?> root) {
        Check.notNullArgument(crit, "crit");
        Check.notNullArgument(propertyName, "propertyName");
        Check.notNullArgument(restrictions, "restrictions");

        FilterRestrictions.addNullAllowed(crit,
                FilterRestrictions.enumFilter(propertyName, restrictions, criteriaBuilder, root));
    }

    /**
     * Apply the limit to the critiera query.
     * @param crit the criteria query
     * @param limit the limit
     */
    public static void setLimit(final Query<?> crit, final Limit limit) {
        Check.notNullArgument(crit, "crit");
        Check.notNullArgument(limit, "limit");

        if (limit.getFirstResult() != null) {
            crit.setFirstResult(limit.getFirstResult());
        }
        if (limit.getMaxResult() != null) {
            crit.setMaxResults(limit.getMaxResult());
        }
    }
}
