/****************************************************************************
** Meta object code from reading C++ file 'categoryediter.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.5.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../demetraAdmin/category/categoryediter.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'categoryediter.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.5.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_categoryEditer_t {
    QByteArrayData data[5];
    char stringdata0[61];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_categoryEditer_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_categoryEditer_t qt_meta_stringdata_categoryEditer = {
    {
QT_MOC_LITERAL(0, 0, 14), // "categoryEditer"
QT_MOC_LITERAL(1, 15, 18), // "changeCategoryName"
QT_MOC_LITERAL(2, 34, 0), // ""
QT_MOC_LITERAL(3, 35, 7), // "newName"
QT_MOC_LITERAL(4, 43, 17) // "editButtonClicked"

    },
    "categoryEditer\0changeCategoryName\0\0"
    "newName\0editButtonClicked"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_categoryEditer[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
       2,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       1,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    1,   24,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       4,    0,   27,    2, 0x0a /* Public */,

 // signals: parameters
    QMetaType::Void, QMetaType::QString,    3,

 // slots: parameters
    QMetaType::Void,

       0        // eod
};

void categoryEditer::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        categoryEditer *_t = static_cast<categoryEditer *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->changeCategoryName((*reinterpret_cast< QString(*)>(_a[1]))); break;
        case 1: _t->editButtonClicked(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (categoryEditer::*_t)(QString );
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&categoryEditer::changeCategoryName)) {
                *result = 0;
            }
        }
    }
}

const QMetaObject categoryEditer::staticMetaObject = {
    { &QWidget::staticMetaObject, qt_meta_stringdata_categoryEditer.data,
      qt_meta_data_categoryEditer,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *categoryEditer::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *categoryEditer::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_categoryEditer.stringdata0))
        return static_cast<void*>(const_cast< categoryEditer*>(this));
    return QWidget::qt_metacast(_clname);
}

int categoryEditer::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QWidget::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 2)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 2;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 2)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 2;
    }
    return _id;
}

// SIGNAL 0
void categoryEditer::changeCategoryName(QString _t1)
{
    void *_a[] = { Q_NULLPTR, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 0, _a);
}
QT_END_MOC_NAMESPACE
