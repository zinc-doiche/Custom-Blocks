package com.github.doiche.lib;

import net.kyori.adventure.util.TriState;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ex)
 * <pre>
 *     {@code
 *      TabBuilder builder = TabBuilder.of(args);
 *      switch (args.length) {
 *          case 1 -> {
 *              if(args[0].equals(GENERATOR)) {
 *                  builder.set(0, ADD, GET, MANAGE);
 *              }
 *              else if (args[0].equals(GENERATOR_ALIAS)) {
 *                  builder.set(0, ADD_ALIAS, GET_ALIAS, MANAGE_ALIAS);
 *              }
 *          }
 *          case 2 -> {
 *              if (args[0].equals(GET) || args[0].equals(GET_ALIAS)) {
 *                  builder.set(1, BlockGenerator.getAllGenerators().keySet()
 *                      .stream().toList());
 *              } else {
 *                  builder.setDefault();
 *              }
 *          }
*          default -> builder.setDefault();
 *      }
 *      return builder.build();
 * </pre>
 */
public class TabBuilder {
    private final String[] args;
    private List<String> result;
    private TriState isValid = TriState.NOT_SET;

    private TabBuilder(String[] args) {
        this.args = args;
    }

    public static TabBuilder of(String[] args) {
        return new TabBuilder(args);
    }

    public void set(int idx, List<String> suggestions) {
        if(isValid != TriState.FALSE) {
            result = StringUtil.copyPartialMatches(args[idx], suggestions, new ArrayList<>());
        } else {
            result = new ArrayList<>();
        }
        isValid = TriState.NOT_SET;
    }

    public void set(int idx, String... suggestions) {
        set(idx, Arrays.asList(suggestions));
    }

    public TabBuilder hasBefore(int idx, String... before) {
        isValid = TriState.byBoolean(Arrays.asList(before).contains(args[idx - 1]));
        return this;
    }

    public void setDefault() {
        result = new ArrayList<>();
    }

    public List<String> build() {
        return result;
    }
}
