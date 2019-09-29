/****************************************************************************
** Meta object code from reading C++ file 'positions.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.5.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../demetraAdmin/positions/positions.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'positions.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.5.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_positions_t {
    QByteArrayData data[9];
    char stringdata0[106];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_positions_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_positions_t qt_meta_stringdata_positions = {
    {
QT_MOC_LITERAL(0, 0, 9), // "positions"
QT_MOC_LITERAL(1, 10, 19), // "deletePositionLater"
QT_MOC_LITERAL(2, 30, 0), // ""
QT_MOC_LITERAL(3, 31, 22), // "editPostionButtonEvent"
QT_MOC_LITERAL(4, 54, 4), // "edit"
QT_MOC_LITERAL(5, 59, 4), // "name"
QT_MOC_LITERAL(6, 64, 4), // "cost"
QT_MOC_LITERAL(7, 69, 17), // "deleteButtonEvent"
QT_MOC_LITERAL(8, 87, 18) // "loadIconLabelEvent"

    },
    "positions\0deletePositionLater\0\0"
    "editPostionButtonEvent\0edit\0name\0cost\0"
    "deleteButtonEvent\0loadIconLabelEvent"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_positions[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
       5,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       1,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    0,   39,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       3,    0,   40,    2, 0x08 /* Private */,
       4,    2,   41,    2, 0x08 /* Private */,
       7,    0,   46,    2, 0x08 /* Private */,
       8,    0,   47,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString, QMetaType::QString,    5,    6,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void positions::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        positions *_t = static_cast<positions *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->deletePositionLater(); break;
        case 1: _t->editPostionButtonEvent(); break;
        case 2: _t->edit((*reinterpret_cast< QString(*)>(_a[1])),(*reinterpret_cast< QString(*)>(_a[2]))); break;
        case 3: _t->deleteButtonEvent(); break;
        case 4: _t->loadIconLabelEvent(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (positions::*_t)();
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&positions::deletePositionLater)) {
                *result = 0;
            }
        }
    }
}

const QMetaObject positions::staticMetaObject = {
    { &QWidget::staticMetaObject, qt_meta_stringdata_positions.data,
      qt_meta_data_positions,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *positions::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *positions::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_positions.stringdata0))
        return static_cast<void*>(const_cast< positions*>(this));
    return QWidget::qt_metacast(_clname);
}

int positions::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QWidget::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 5)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 5;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 5)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 5;
    }
    return _id;
}

// SIGNAL 0
void positions::deletePositionLater()
{
    QMetaObject::activate(this, &staticMetaObject, 0, Q_NULLPTR);
}
QT_END_MOC_NAMESPACE
