# QNameParser

### Short description <br>

Entity class QName describe qualified name.
Class _QNameParser_ produces an object of qualified name, using regular expressions.
<br>The format of a qualified name is specified by the next rules:

name                ::= simplename | prefixedname<br>
simplename          ::= onecharsimplename | twocharsimplename | threeormorecharname<br>
prefixedname        ::= prefix ':' localname<br>
localname           ::= onecharlocalname | twocharlocalname | threeormorecharname<br>
onecharsimplename   ::= Any Unicode character except: '.', '/', ':', '[', ']', '\*', ''', '"', '|' or any whitespace character                                                              
twocharsimplename   ::= '.' onecharsimplename | onecharsimplename '.' | onecharsimplename onecharsimplename<br>
onecharlocalname    ::= nonspace<br>
twocharlocalname    ::= nonspace nonspace<br>
threeormorecharname ::= nonspace string nonspace<br>
prefix              ::= Any valid non-empty XML name<br>
string              ::= char | string char<br>
char                ::= nonspace | ' '<br>
nonspace            ::= (Any Unicode character except: '/', ':', '[', ']', '\*', ''', '"', '|' or any whitespace character<br>


### Run tests
1) First of all, clone the repository with `git clone`.
1) Go to the directory with cloned repository (`cd` or something).
1) Build project with _maven_:
	```bash
	mvn clean install
	```
1) Run the _QNameParserTest_ class 
	```bash
	mvn clean test -Dtest=com.exoplatform.testtask.qname.QNameParserTest
	```