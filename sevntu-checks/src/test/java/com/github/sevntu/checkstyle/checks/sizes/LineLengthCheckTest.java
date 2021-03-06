package com.github.sevntu.checkstyle.checks.sizes;

import static com.github.sevntu.checkstyle.checks.sizes.LineLengthExtendedCheck.*;

import org.junit.Test;

import com.github.sevntu.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;

public class LineLengthCheckTest extends BaseCheckTestSupport
{
	@Test
	public void testSimple()
			throws Exception
	{
		final DefaultConfiguration checkConfig =
				createCheckConfig(LineLengthExtendedCheck.class);
		checkConfig.addAttribute("max", "80");
		checkConfig.addAttribute("ignorePattern", "^.*is OK.*regexp.*$");
		final String[] expected = {
				"18: " + getCheckMessage(MSG_KEY, 80, 81),
				"145: " + getCheckMessage(MSG_KEY, 80, 83),
		};
		verify(checkConfig, getPath("InputSimple.java"), expected);
	}

	@Test
	public void testSimpleIgnore()
			throws Exception
	{
		final DefaultConfiguration checkConfig =
				createCheckConfig(LineLengthExtendedCheck.class);
		checkConfig.addAttribute("max", "40");
		checkConfig.addAttribute("ignorePattern", "^.*is OK.*regexp.*$");
		final String[] expected = {
				"1: " + getCheckMessage(MSG_KEY, 40, 80),
				"5: " + getCheckMessage(MSG_KEY, 40, 80),
				"6: " + getCheckMessage(MSG_KEY, 40, 50),
				"18: " + getCheckMessage(MSG_KEY, 40, 81),
				"101: " + getCheckMessage(MSG_KEY, 40, 45),
				"125: " + getCheckMessage(MSG_KEY, 40, 42),
				"128: " + getCheckMessage(MSG_KEY, 40, 43),
				"132: " + getCheckMessage(MSG_KEY, 40, 43),
				"145: " + getCheckMessage(MSG_KEY, 40, 83),
				"146: " + getCheckMessage(MSG_KEY, 40, 78),
				"148: " + getCheckMessage(MSG_KEY, 40, 57),
				"151: " + getCheckMessage(MSG_KEY, 40, 74),
				"152: " + getCheckMessage(MSG_KEY, 40, 75),
				"192: " + getCheckMessage(MSG_KEY, 40, 66),
				"200: " + getCheckMessage(MSG_KEY, 40, 58),
				"207: " + getCheckMessage(MSG_KEY, 40, 50),
		};
		checkConfig.addAttribute("ignoreClass", "true");
		checkConfig.addAttribute("ignoreConstructor", "true");
		checkConfig.addAttribute("ignoreField", "true");
		checkConfig.addAttribute("ignoreMethod", "true");
		verify(checkConfig, getPath("InputSimple.java"), expected);
	}
}
