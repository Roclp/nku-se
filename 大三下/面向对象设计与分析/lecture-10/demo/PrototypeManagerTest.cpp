// PrototypeManagerTest.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "PrototypeManagerTest.h"
#include "PrototypeManager.h"
#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// The one and only application object

CWinApp theApp;

using namespace std;

int _tmain(int argc, TCHAR* argv[], TCHAR* envp[])
{
	int nRetCode = 0;

	// initialize MFC and print and error on failure
	if (!AfxWinInit(::GetModuleHandle(NULL), NULL, ::GetCommandLine(), 0))
	{
		// TODO: change error code to suit your needs
		_tprintf(_T("Fatal Error: MFC initialization failed\n"));
		nRetCode = 1;
	}
	else
	{
		CPrototypeManager	prototypeManager;

		// Create and add a Pipe prototyope
		CConcretePipePrototype* newPipe1 = new CConcretePipePrototype();
		ASSERT( newPipe1 );

		newPipe1->Name("Pipe #1");
		newPipe1->InsideDiameterFt(6.0);
		newPipe1->OutSideDiamterFt(7.0);
		newPipe1->AbsoluteRoughness(0.01);

		prototypeManager.AddPrototype( newPipe1 );

		// Create and add another Pipe prototyope
		CConcretePipePrototype* newPipe2 = new CConcretePipePrototype();
		ASSERT( newPipe2 );

		newPipe2->Name("Pipe #2");
		newPipe2->InsideDiameterFt(4.0);
		newPipe2->OutSideDiamterFt(5.0);
		newPipe2->AbsoluteRoughness(0.02);

		prototypeManager.AddPrototype( newPipe2 );

		// Create amd add a compressor prototype
		CConcreteCompressorPrototype* newCompressor1 = new CConcreteCompressorPrototype();
		ASSERT( newCompressor1 );

		newCompressor1->Name("Compressor #1");
		newCompressor1->NumberOfStages(2);
		newCompressor1->HpPerStage(20);
		newCompressor1->Efficiency(0.70);

		prototypeManager.AddPrototype( newCompressor1 );

		// Get a list of the current prototypes in the manger
		std::list<CString> availiblePrototypes = prototypeManager.AvailablePrototypes();

		// Loop through and print out the list of prototypes
		//cout << CString("/nPrototypes In Manager:/n/n");
		printf("\nPrototypes In Manager:\n\n");

		for ( std::list<CString>::iterator index = availiblePrototypes.begin(); index != availiblePrototypes.end(); index++ )
		{
			printf(*index);
			printf("\n");
		}

		// Find Pipe#2 prototype in the manager
		CBasePrototype* existingObject = prototypeManager.FindPrototype("Pipe #2");

		// If Pipe#2 prototype found, then create a copy and add it to the prototype manager
		if ( existingObject )
		{
			//Create a copy and add it to the manager
			CBasePrototype* newObject = existingObject->Clone();
			ASSERT( newObject );

			newObject->Name("Pipe #3");

			prototypeManager.AddPrototype( newObject );
		}

		// Get a list of the current prototypes in the manger
		availiblePrototypes = prototypeManager.AvailablePrototypes();

		// Loop through and print out the list of prototypes
		//cout << CString("/nPrototypes In Manager:/n/n");
		printf("\nPrototypes In Manager:\n\n");

		for ( std::list<CString>::iterator index = availiblePrototypes.begin(); index != availiblePrototypes.end(); index++ )
		{
			printf(*index);
			printf("\n");
		}
	}

	return nRetCode;
}
