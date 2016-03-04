import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import dk.dren.hunspell.Hunspell;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.om.SequenceTool;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.value.AtomicValue;
import net.sf.saxon.value.BooleanValue;
import net.sf.saxon.value.SequenceExtent;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.value.StringValue;

public class Spellcheck extends ExtensionFunctionDefinition {
	private static final long serialVersionUID = 1L;

	@Override
	public StructuredQName getFunctionQName() {
		return new StructuredQName("letex", "http://www.le-tex.de/namespace", "spellcheck");
	}

	@Override
	public SequenceType[] getArgumentTypes() {
		return new SequenceType[] { SequenceType.SINGLE_STRING, SequenceType.SINGLE_STRING };
	}

	@Override
	public SequenceType getResultType(SequenceType[] suppliedArgumentTypes) {
		return SequenceType.ATOMIC_SEQUENCE;
	}

	@Override
	public ExtensionFunctionCall makeCallExpression() {
		return new ExtensionFunctionCall() {
			private static final long serialVersionUID = 1L;

			@Override
			public Sequence call(XPathContext context, Sequence[] arguments) throws XPathException {
				Hunspell.Dictionary dict = null;
				try {
					dict = Hunspell.getInstance().getDictionary("dicts/"+SequenceTool.getStringValue(arguments[1]));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (UnsatisfiedLinkError e) {
					e.printStackTrace();
				} catch (UnsupportedOperationException e) {
					e.printStackTrace();
				}
				
				String word = SequenceTool.getStringValue(arguments[0]);
				
				List<AtomicValue> list = new ArrayList<AtomicValue>();
				list.add(BooleanValue.get(dict.misspelled(word)));
				for (String suggestion : dict.suggest(word)) {
					list.add(new StringValue(suggestion));
				}
				
				return new SequenceExtent<AtomicValue>(list);
			}
		};
	}
}