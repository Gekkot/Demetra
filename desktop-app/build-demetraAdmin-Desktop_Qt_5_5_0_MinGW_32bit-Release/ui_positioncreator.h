/********************************************************************************
** Form generated from reading UI file 'positioncreator.ui'
**
** Created by: Qt User Interface Compiler version 5.5.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_POSITIONCREATOR_H
#define UI_POSITIONCREATOR_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_positionCreator
{
public:
    QPushButton *saveButton;
    QLabel *label;
    QLineEdit *nameLineEdit;
    QLabel *label_2;
    QLineEdit *costLineEdit;

    void setupUi(QWidget *positionCreator)
    {
        if (positionCreator->objectName().isEmpty())
            positionCreator->setObjectName(QStringLiteral("positionCreator"));
        positionCreator->resize(363, 138);
        saveButton = new QPushButton(positionCreator);
        saveButton->setObjectName(QStringLiteral("saveButton"));
        saveButton->setGeometry(QRect(230, 100, 93, 28));
        label = new QLabel(positionCreator);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(20, 10, 91, 16));
        nameLineEdit = new QLineEdit(positionCreator);
        nameLineEdit->setObjectName(QStringLiteral("nameLineEdit"));
        nameLineEdit->setGeometry(QRect(130, 10, 191, 22));
        label_2 = new QLabel(positionCreator);
        label_2->setObjectName(QStringLiteral("label_2"));
        label_2->setGeometry(QRect(20, 60, 55, 16));
        costLineEdit = new QLineEdit(positionCreator);
        costLineEdit->setObjectName(QStringLiteral("costLineEdit"));
        costLineEdit->setGeometry(QRect(130, 60, 191, 22));

        retranslateUi(positionCreator);

        QMetaObject::connectSlotsByName(positionCreator);
    } // setupUi

    void retranslateUi(QWidget *positionCreator)
    {
        positionCreator->setWindowTitle(QApplication::translate("positionCreator", "Form", 0));
        saveButton->setText(QApplication::translate("positionCreator", "\320\241\320\276\321\205\321\200\320\260\320\275\320\270\321\202\321\214", 0));
        label->setText(QApplication::translate("positionCreator", "\320\235\320\260\320\270\320\274\320\265\320\275\320\276\320\262\320\260\320\275\320\270\320\265", 0));
        label_2->setText(QApplication::translate("positionCreator", "\320\246\320\265\320\275\320\260", 0));
    } // retranslateUi

};

namespace Ui {
    class positionCreator: public Ui_positionCreator {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_POSITIONCREATOR_H
