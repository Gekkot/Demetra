/****************************************************************************
** Meta object code from reading C++ file 'mainwindow.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.5.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../../demetraAdmin/mainwindow.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'mainwindow.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.5.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_MainWindow_t {
    QByteArrayData data[15];
    char stringdata0[254];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_MainWindow_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_MainWindow_t qt_meta_stringdata_MainWindow = {
    {
QT_MOC_LITERAL(0, 0, 10), // "MainWindow"
QT_MOC_LITERAL(1, 11, 28), // "on_addCategoryButton_clicked"
QT_MOC_LITERAL(2, 40, 0), // ""
QT_MOC_LITERAL(3, 41, 27), // "on_addToppingButton_clicked"
QT_MOC_LITERAL(4, 69, 33), // "on_sendMenuToServerButton_cli..."
QT_MOC_LITERAL(5, 103, 13), // "creatCategory"
QT_MOC_LITERAL(6, 117, 12), // "nameCategory"
QT_MOC_LITERAL(7, 130, 21), // "showAddCategoryWidget"
QT_MOC_LITERAL(8, 152, 19), // "deleteCategoryEvent"
QT_MOC_LITERAL(9, 172, 21), // "showAddPositionWidget"
QT_MOC_LITERAL(10, 194, 13), // "creatPosition"
QT_MOC_LITERAL(11, 208, 4), // "name"
QT_MOC_LITERAL(12, 213, 4), // "cost"
QT_MOC_LITERAL(13, 218, 15), // "categoryClicked"
QT_MOC_LITERAL(14, 234, 19) // "repaintPositionList"

    },
    "MainWindow\0on_addCategoryButton_clicked\0"
    "\0on_addToppingButton_clicked\0"
    "on_sendMenuToServerButton_clicked\0"
    "creatCategory\0nameCategory\0"
    "showAddCategoryWidget\0deleteCategoryEvent\0"
    "showAddPositionWidget\0creatPosition\0"
    "name\0cost\0categoryClicked\0repaintPositionList"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_MainWindow[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
      10,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       0,       // signalCount

 // slots: name, argc, parameters, tag, flags
       1,    0,   64,    2, 0x08 /* Private */,
       3,    0,   65,    2, 0x08 /* Private */,
       4,    0,   66,    2, 0x08 /* Private */,
       5,    1,   67,    2, 0x08 /* Private */,
       7,    0,   70,    2, 0x08 /* Private */,
       8,    0,   71,    2, 0x08 /* Private */,
       9,    0,   72,    2, 0x08 /* Private */,
      10,    2,   73,    2, 0x08 /* Private */,
      13,    0,   78,    2, 0x08 /* Private */,
      14,    0,   79,    2, 0x0a /* Public */,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString,    6,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString, QMetaType::QString,   11,   12,
    QMetaType::Void,
    QMetaType::Void,

       0        // eod
};

void MainWindow::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        MainWindow *_t = static_cast<MainWindow *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->on_addCategoryButton_clicked(); break;
        case 1: _t->on_addToppingButton_clicked(); break;
        case 2: _t->on_sendMenuToServerButton_clicked(); break;
        case 3: _t->creatCategory((*reinterpret_cast< QString(*)>(_a[1]))); break;
        case 4: _t->showAddCategoryWidget(); break;
        case 5: _t->deleteCategoryEvent(); break;
        case 6: _t->showAddPositionWidget(); break;
        case 7: _t->creatPosition((*reinterpret_cast< QString(*)>(_a[1])),(*reinterpret_cast< QString(*)>(_a[2]))); break;
        case 8: _t->categoryClicked(); break;
        case 9: _t->repaintPositionList(); break;
        default: ;
        }
    }
}

const QMetaObject MainWindow::staticMetaObject = {
    { &QMainWindow::staticMetaObject, qt_meta_stringdata_MainWindow.data,
      qt_meta_data_MainWindow,  qt_static_metacall, Q_NULLPTR, Q_NULLPTR}
};


const QMetaObject *MainWindow::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *MainWindow::qt_metacast(const char *_clname)
{
    if (!_clname) return Q_NULLPTR;
    if (!strcmp(_clname, qt_meta_stringdata_MainWindow.stringdata0))
        return static_cast<void*>(const_cast< MainWindow*>(this));
    return QMainWindow::qt_metacast(_clname);
}

int MainWindow::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QMainWindow::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 10)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 10;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 10)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 10;
    }
    return _id;
}
QT_END_MOC_NAMESPACE
