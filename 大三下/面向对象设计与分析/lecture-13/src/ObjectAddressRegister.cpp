#include <iostream>
#include <set>
#include <sstream>

using namespace std;

template<typename T>
class ObjectAddressRegister {
public:
    ObjectAddressRegister(){
        cout<< "ObjectAddressRegister, this = " << (void*)this << "\n";

        // Although at the time this function is called the object has not
        // yes been constructed completely, its address is valid so we can
        // register the address.
        addresses.insert( (void*)this );
    };

    virtual ~ObjectAddressRegister(){
        addresses.erase( (void*) this );
    }

    static bool checkExist(void* pointer, bool nonExistIsError = true){
        if (addresses.find(pointer) != addresses.end() ){
            cout << "ObjectAddressRegister, pointer beging checked exist. \n"
                    << "  pointer = " << (void*) pointer << "\n"
                    << "  typeid  = " << typeid(T).name() << "\n";
            return true;
        }

        // report the non-exist.
        std::ostringstream os;
        os<< "can not find the pointer in ObjectAddressRegister.\n"
          << "  pointer       = " << pointer << "\n"
          << "  number of registered addresses = " << addresses.size() << "\n"
          << "  ";
        for (auto e: addresses){
            os << e << " ";
        }
        if ( nonExistIsError ){
            os << "\nThis is an error";
        }
        cout << "ObjectAddressRegister" <<  os.str() << "\n";
        return false;
    }

    // check whether a binary block pointed by pointer contains a
    // registered object address.
    static bool checkExist(const void* pointer, size_t size,
                           bool nonExistIsError = true){
        if (size != sizeof(const void*) ) {
            return false;
        }        
        return checkExist( pointer, nonExistIsError );
    }

protected:
    static std::set<void*> addresses;
};

template<typename T>
std::set<void*> ObjectAddressRegister<T>::addresses;

class Event: ObjectAddressRegister<Event> {
public:
    void someFunction(){};
};

int main()
{
    Event event;
    ObjectAddressRegister<Event>::checkExist( &event );
    ObjectAddressRegister<Event>::checkExist( (Event*) 1000 );
}
