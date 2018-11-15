import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.regex.Matcher
import java.util.regex.Pattern

import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


/**
 * a wrapper class of the values of id attribute contained in 
 * <span id="select2-bot_task_action_NNN-container">
 */
class HamzasIdentifier {
	
	private static final Pattern p = Pattern.compile('select2-bot_task_action_(\\d+)-container')
	private String idValue_ = null
	private Integer sequenceNumber_ = 0
	HamzasIdentifier(String idValue) {
		idValue_ = idValue.trim()
		Matcher m = p.matcher(idValue_)
		if (m.matches()) {
			sequenceNumber_ = Integer.parseInt(m.group(1))
		}
	}
	
	/**
	 * 
	 * @return NNN as Integer
	 */
	Integer getSequenceNumber() {
		return sequenceNumber_
	}
	
	@Override
	boolean equals(Object obj) {
		if (obj == null)
			return false
		if (!(obj instanceof HamzasIdentifier))
			return false
		if (obj == this)
			return true
		return this.getSequenceNumber() == ((HamzasIdentifier)obj).getSequenceNumber()
	}
	
	@Override
	int hashCode() {
		return this.getSequenceNumber()
	}
	
	@Override
	String toString() {
		return idValue_
	}
}

WebUI.openBrowser('')

WebUI.navigateToUrl('http://demoaut-mimic.kazurayam.com/testbed_7527.html')

// select the group of <span> elements with id attribute which starts with string 'select2-bot_task_action_'
List<WebElement> spans = WebUI.findWebElements(findTestObject('Object Repository/Page_Katalon Discussion 7527/span_Email'), 10)

List<HamzasIdentifier> idList = new ArrayList<HamzasIdentifier>()
for (WebElement span : spans) {
	String idStr = span.getAttribute("id")
	HamzasIdentifier id = new HamzasIdentifier(idStr)
	idList.add(id)
	//WebUI.comment(">>> id is ${id}, id.getSequenceNumber() is ${id.getSequenceNumber()}")
}

// sort the list of HamzasIdentifiers in reverseOrder
Collections.sort(
	idList,
	new Comparator<HamzasIdentifier>() {
		@Override
		public int compare(HamzasIdentifier id1, HamzasIdentifier id2) {
			return id2.getSequenceNumber() - id1.getSequenceNumber()     // sort in reverse order
		}
	}
	);

//for (HamzasIdentifier id : idList) {
//	WebUI.comment("<<< id is ${id}, id.getSequenceNumber() is ${id.getSequenceNumber()}")
//}

// pick up the id of the <span id="${theLatest}"> which has the largest sequenceNumber, 
// possibly the element was last created
HamzasIdentifier theLatest = idList.get(0)
WebUI.comment(">>> the latest one is ${theLatest.toString()}")

WebUI.closeBrowser()

