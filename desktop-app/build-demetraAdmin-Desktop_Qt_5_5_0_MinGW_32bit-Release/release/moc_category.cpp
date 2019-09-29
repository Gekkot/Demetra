/****************************************************************************
** Meta object code from reading C++ file 'category.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.5.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../demetraAdmin/category/category.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'category.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.5.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_Category_t {
    QByteArrayData data[11];
    char stringdata0[179];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_Category_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_Category_t qt_meta_stringdata_Category = {
    {
QT_MOC_LITERAL(0, 0, 8), // "Category"
QT_MOC_LITERAL(1, 9, 19), // "deleteCategoryLater"
QT_MOC_LITERAL(2, 29, 0), // ""
QT_MOC_LITERAL(3, 30, 22), // "addPositionButtonEvent"
QT_MOC_LITERAL(4, 53, 19), // "categoryViewClicked"
QT_MOC_LITERAL(5, 73, 19), // "editNameButtonEvent"
QT_MOC_LITERAL(6, 93, 8), // "editName"
QT_MOC_LITERAL(7, 102, 7), // "newName"
QT_MOC_LITERAL(8, 110, 17), // "deleteButtonEvent"
QT_MOC_LITERAL(9, 128, 24), // "addPositionButtonClicked"
QT_MOC_LITERAL(10, 153, 25) // "categoryNameButtonClicked"

    },
    "Category\0deleteCategoryLater\0\0"
    "addPositionButtonEvent\0categoryViewClicked\0"
    "editNameButtonEvent\0editName\0newName\0"
    "deleteButtonEvent\0addPositionButtonClicked\0"
    "categoryNameButtonClicked"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_Category[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
       8,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       3,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    0,   54,    2, 0x06 /* Public */,
       3,    0,   55,    2, 0x06 /* Public */,
       4,    0,   56,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       5,    0,   57,    2, 0x08 /* Private */,
       6,    1,   58,    2, 0x08 /* Private */,
       8,    0,   61,    2, 0x08 /* Private */,
       9,    0,   62,    2, 0x08 /* Private */,
      10,    0,   63,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString,    7,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void Category::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        Category *_t = static_cast<Category *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->deleteCategoryLater(); break;
        case 1: _t->addPositionButtonEvent(); break;
        case 2: _t->categoryViewClicked(); break;
        case 3: _t->editNameButtonEvent(); break;
        case 4: _t->editName((*reinterpret_cast< QString(*)>(_a[1]))); break;
        case 5: _t->deleteButtonEvent(); break;
        case 6: _t->addPositionButtonClicked(); break;
        case 7: _t->categoryNameButtonClicked(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (Category::*_t)();
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&Category::deleteCategoryLater)) {
                *result = 0;
            }
        }
        {
            typedef void (Category::*_t)();
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&Category::addPositionButtonEvent)) {
                *result = 1;
            }
        }
        {
            typedef void (Category::*_t)();
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&Category::categoryViewClicked)) {
                *result = 2;
            }
        }
    }
}

const QMetaObject Category::staticMetaObject = {
    { &QWidget::staticMetaObject, qt_meta_stringdata_Category.data,
      qt_meta_data_Category,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *Category::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *Category::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_Category.stringdata0))
        return static_cast<void*>(const_cast< Category*>(this));
    return QWidget::qt_metacast(_clname);
}

int Category::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QWidget::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 8)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 8;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 8)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 8;
    }
    return _id;
}

// SIGNAL 0
void Category::deleteCategoryLater()
{
    QMetaObject::activate(this, &staticMetaObject, 0, Q_NULLPTR);
}

// SIGNAL 1
void Category::addPositionButtonEvent()
{
    QMetaObject::activate(this, &staticMetaObject, 1, Q_NULLPTR);
}

// SIGNAL 2
void Category::categoryViewClicked()
{
    QMetaObject::activate(this, &staticMetaObject, 2, Q_NULLPTR);
}
QT_END_MOC_NAMESPACE
