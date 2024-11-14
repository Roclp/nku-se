#include "stdafx.h"
#include "PrototypeManager.h"

CBasePrototype::CBasePrototype( const CBasePrototype& copy ) :
_name( copy._name )
{};

CConcretePipePrototype::CConcretePipePrototype() :
_insideDiameterFt( 0.0 ),
_outsideDiameterFt( 0.0 ),
_absoluteRoughnessInches( 0.0 )
{}

CConcretePipePrototype::CConcretePipePrototype( const CConcretePipePrototype& copy ) :
_insideDiameterFt( copy._insideDiameterFt ),
_outsideDiameterFt( copy._outsideDiameterFt ),
_absoluteRoughnessInches( copy._absoluteRoughnessInches )
{}

CConcretePipePrototype&	
CConcretePipePrototype::operator=(	const CConcretePipePrototype	&other )
{
	_insideDiameterFt = other._insideDiameterFt;
	_outsideDiameterFt = other._outsideDiameterFt;
	_absoluteRoughnessInches = other._absoluteRoughnessInches;

	return (*this);
}

CBasePrototype*
CConcretePipePrototype::Clone() const
{
	return new CConcretePipePrototype(*this);
}

CConcreteCompressorPrototype::CConcreteCompressorPrototype() :
_numberOfStages( 0 ),
_hpPerStage( 0.0 ),
_efficiency( 0.0 )
{}

CConcreteCompressorPrototype::CConcreteCompressorPrototype( const CConcreteCompressorPrototype& copy ) :
_numberOfStages( copy._numberOfStages ),
_hpPerStage( copy._hpPerStage ),
_efficiency( copy._efficiency )
{}

CBasePrototype*
CConcreteCompressorPrototype::Clone() const
{
	return new CConcreteCompressorPrototype(*this);
}



CPrototypeManager::CPrototypeManager()
{
	DeleteAllPrototypes();
}

CPrototypeManager::CPrototypeManager( const CPrototypeManager& copy )
{
	DeleteAllPrototypes();

	for ( std::list<CBasePrototype*>::const_iterator index = copy._prototypeList.begin(); index != copy._prototypeList.end(); index++ )
	{
		_prototypeList.push_back( (*index) );
	}
}

CPrototypeManager::~CPrototypeManager()
{
	DeleteAllPrototypes();
}

void			
CPrototypeManager::AddPrototype( CBasePrototype* prototype )
{
	// If prototype exists, add it to the list
	if ( prototype )
	{
		// Check for and remove any existing prototype object in the list with the same name
		if ( NULL != FindPrototype( prototype->Name() ) )
			DeletePrototype( prototype->Name() );

		// Add the passed in prototype to the end of the list
		_prototypeList.push_back( prototype );
	}
}

void
CPrototypeManager::DeletePrototype( const CString label )
{
	CBasePrototype* currentPrototype = NULL;

	// Traverse the list and delete the prototype objects that matches the label
	for ( std::list<CBasePrototype*>::iterator index = _prototypeList.begin(); index != _prototypeList.end(); index++ )
	{
		if ( label == (*index)->Name() )
		{
			currentPrototype = (*index);
			delete currentPrototype;

			index = _prototypeList.erase(index);
			break;
		}
	}
}

void
CPrototypeManager::DeleteAllPrototypes()
{
	CBasePrototype* currentPrototype = NULL;

	// Traverse the list and delete the prototype objects
	for ( std::list<CBasePrototype*>::iterator index = _prototypeList.begin(); index != _prototypeList.end(); index++ )
	{
		currentPrototype = (*index);
		delete currentPrototype;
	}

	// Clear the list
	_prototypeList.clear();
}

CBasePrototype*	
CPrototypeManager::FindPrototype(  const CString label )
{
	CBasePrototype* returnValue = NULL;

	// Traverse the list and delete the prototype objects
	for ( std::list<CBasePrototype*>::iterator index = _prototypeList.begin(); index != _prototypeList.end(); index++ )
	{
		if ( label == (*index)->Name() )
		{
			returnValue = (*index);
			break;
		}
	}

	return returnValue;
}

const CBasePrototype*	
CPrototypeManager::FindPrototype(  const CString label ) const
{
	const CBasePrototype* returnValue = NULL;

	// Traverse the list and delete the prototype objects
	for ( std::list<CBasePrototype*>::const_iterator index = _prototypeList.begin(); index != _prototypeList.end(); index++ )
	{
		if ( label == (*index)->Name() )
		{
			returnValue = (*index);
			break;
		}
	}

	return returnValue;

}

std::list<CString>
CPrototypeManager::AvailablePrototypes() const
{
	std::list<CString> returnValue;

	// Traverse the list and delete the prototype objects
	for ( std::list<CBasePrototype*>::const_iterator index = _prototypeList.begin(); index != _prototypeList.end(); index++ )
	{
		returnValue.push_back( (*index)->Name() );
	}

	return returnValue;
}


