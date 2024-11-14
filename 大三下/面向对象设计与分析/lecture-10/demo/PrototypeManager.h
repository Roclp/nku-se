#ifndef	__PROTOTYPEMANAGER_H__
#define	__PROTOTYPEMANAGER_H__

#include <list>

class CBasePrototype
{

protected:            

      CString           _name;

public:

      CBasePrototype();
      CBasePrototype( const CBasePrototype& copy );
      ~CBasePrototype();


       virtual CBasePrototype*    Clone() const=0;


      void        Name( CString name );
      CString     Name() const;

};

inline				CBasePrototype::CBasePrototype()			{};
inline				CBasePrototype::~CBasePrototype()			{};
inline void			CBasePrototype::Name( CString name )		{ _name = name; }
inline CString		CBasePrototype::Name() const				{ return _name; }

class CConcretePipePrototype : public CBasePrototype
{

protected:            

      double	       _insideDiameterFt;
	  double	       _outsideDiameterFt;
	  double		   _absoluteRoughnessInches;

public:

      CConcretePipePrototype(); 
      CConcretePipePrototype( const CConcretePipePrototype& copy );
      ~CConcretePipePrototype(); 

	  virtual CConcretePipePrototype&	operator=(	const CConcretePipePrototype	&other );

      virtual CBasePrototype*    Clone() const; 

      void				InsideDiameterFt( double 	insideDiameterFt );
	  void				OutSideDiamterFt( double	outsideDiameterFt );
	  void				AbsoluteRoughness( double	absoluteRoughnessInches );

      double			InsideDiameterFt() const;
	  double			OutSideDiamterFt() const;
	  double			AbsoluteRoughness() const;

}; 

inline				CConcretePipePrototype::~CConcretePipePrototype()										{};
inline void			CConcretePipePrototype::InsideDiameterFt( double 	insideDiameterFt )					{ _insideDiameterFt = insideDiameterFt; }
inline void			CConcretePipePrototype::OutSideDiamterFt( double	outsideDiameterFt )					{ _outsideDiameterFt = outsideDiameterFt; }
inline void			CConcretePipePrototype::AbsoluteRoughness( double	absoluteRoughnessInches )			{ _absoluteRoughnessInches = absoluteRoughnessInches; }
inline double		CConcretePipePrototype::InsideDiameterFt() const										{ return _insideDiameterFt; }
inline double		CConcretePipePrototype::OutSideDiamterFt() const										{ return _outsideDiameterFt; }
inline double		CConcretePipePrototype::AbsoluteRoughness() const										{ return _absoluteRoughnessInches; }


class CConcreteCompressorPrototype : public CBasePrototype
{

protected:            

      int			_numberOfStages;
	  double	    _hpPerStage;
	  double		_efficiency;

public:

      CConcreteCompressorPrototype(); 
      CConcreteCompressorPrototype( const CConcreteCompressorPrototype& copy );
      ~CConcreteCompressorPrototype(); 

       virtual CBasePrototype*    Clone() const; 

      void				NumberOfStages( int 	numberOfStages );
	  void				HpPerStage( double		hpPerStage );
	  void				Efficiency( double		efficiency );

      int				NumberOfStages() const;
	  double			HpPerStage() const;
	  double			Efficiency() const;

}; 

inline				CConcreteCompressorPrototype::~CConcreteCompressorPrototype()							{};
inline void			CConcreteCompressorPrototype::NumberOfStages( int 	numberOfStages )					{ _numberOfStages = numberOfStages; }
inline void			CConcreteCompressorPrototype::HpPerStage( double		hpPerStage )					{ _hpPerStage = hpPerStage; }
inline void			CConcreteCompressorPrototype::Efficiency( double		efficiency )					{ _efficiency = efficiency; }		
inline int			CConcreteCompressorPrototype::NumberOfStages() const									{ return _numberOfStages; }
inline double		CConcreteCompressorPrototype::HpPerStage() const										{ return _hpPerStage; }
inline double		CConcreteCompressorPrototype::Efficiency() const										{ return _efficiency; }		

class CPrototypeManager
{
	
protected:

	std::list<CBasePrototype*>	_prototypeList;

public:

	CPrototypeManager();
	CPrototypeManager( const CPrototypeManager& copy );
	~CPrototypeManager();


	unsigned int			NumberOfPrototypes() const;

	void					AddPrototype( CBasePrototype* prototype );
	void					DeletePrototype( const CString label );
	void					DeleteAllPrototypes();

	CBasePrototype*			FindPrototype(  const CString label );
	const CBasePrototype*	FindPrototype(  const CString label ) const;

	std::list<CString>		AvailablePrototypes() const;
		
};

#endif

	

