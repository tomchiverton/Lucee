component extends = "org.lucee.cfml.test.LuceeTestCase" labels="component" {
	
	function run( testResults, testBox ){
		// all your suites go here.
		describe( "LDEV-4507", function(){

			it( title="call getComponentMetaData", skip=true, body= function(){
				systemOutput(getComponentMetadata("LDEV4507.test4507simple")); // Error (java.lang.VerifyError) Message Bad type on operand stack
			} );

			it( title="call getComponentMetaData reduced", skip=true, body=function(){
				// timeout=120
				systemOutput(getComponentMetadata("LDEV4507.test4507")); // Error (java.lang.VerifyError) Message Bad type on operand stack
			} );

			it( title="call getComponentMetaData quoted number", body=function(){
				// timeout="120"
				systemOutput(getComponentMetadata("LDEV4507.test4507quoted"));
			} );

		} );
	}

}
