#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QPushButton>
#include <QtNetwork>


/*
 Комментарии? Нееее, не слышал....
 */
MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    this->setMinimumSize(1300,600);
    this->setWindowTitle("Demetra admin");

    categoryCount = 0;

    connect(ui->addCategoryButton,SIGNAL(clicked()),this,SLOT(showAddCategoryWidget()));
    currentCategory = "NULL";
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::repaintPositionList()
{

    if(QString::compare(currentCategory,"NULL")==0){
        for(int i=0;i<positionList.count();i++){
            positionList[i]->deleteLater();
        }
        positionList.clear();
    } else {
        for(int k=0;k<positionList.count();k++){
            positionList[k]->hide();
        }

        for(int j=0;j<positionList.count();j++){
            if(QString::compare(currentCategory,positionList[j]->getCategoryID())==0){
                positionList[j]->raise();
                positionList[j]->show();
            }
        }
    }

}


void MainWindow::on_addCategoryButton_clicked(){}

void MainWindow::on_addToppingButton_clicked(){
    _category = new Category(nullptr,tr("Топпинги и добавки"));
    _category->show();
    _category->setObjectName("Topping");
    connect(_category,SIGNAL(deleteCategoryLater()),this,SLOT(deleteCategoryEvent()));
    connect(_category,SIGNAL(addPositionButtonEvent()),this,SLOT(showAddPositionWidget()));
    connect(_category,SIGNAL(categoryViewClicked()),this,SLOT(categoryClicked()));
    _category->editNameButton->setEnabled(false);

    categoryList.append(_category);
    categoryList.last()->setLayout(ui->categoryLayout);
    categoryCount++;

    ui->categoryLayout->addWidget(categoryList.last());

    ui->addToppingButton->setEnabled(false);

    currentCategory = "Topping";

    repaintPositionList();
}

void MainWindow::on_sendMenuToServerButton_clicked()
{

    QNetworkRequest request(QUrl("http://172.20.42.77:4004/menuAdd"));
 request.setHeader(QNetworkRequest::ContentTypeHeader,"application/json" );
//
    //"application/x-www-form-urlencoded"
    QJsonObject json;
    json.insert("item1", "value1");
    json.insert("item2", "value2");

    QNetworkAccessManager* nam = new QNetworkAccessManager(this);

    nam->post(request, QJsonDocument(json).toJson());


    qDebug()<<"okay";




}

void MainWindow::creatCategory(QString nameCategory){

    _category = new Category(nullptr,nameCategory);
    _category->setObjectName(nameCategory);
    connect(_category,SIGNAL(deleteCategoryLater()),this,SLOT(deleteCategoryEvent()));
    connect(_category,SIGNAL(addPositionButtonEvent()),this,SLOT(showAddPositionWidget()));
    connect(_category,SIGNAL(categoryViewClicked()),this,SLOT(categoryClicked()));

    _category->show();
    categoryList.append(_category);
    categoryCount++;
    categoryList.last()->setLayout(ui->categoryLayout);

    ui->categoryLayout->addWidget(categoryList.last());

    currentCategory = nameCategory;

    repaintPositionList();

}

void MainWindow::showAddCategoryWidget(){
    categoryCreator* categoryCreatWidget = new categoryCreator(nullptr);
    connect(categoryCreatWidget,SIGNAL(saveCategoryName(QString)),this,SLOT(creatCategory(QString)));
    categoryCreatWidget->show();
    categoryCreatWidget->setModal(true);
}

void MainWindow::deleteCategoryEvent(){

    QString objectName = QObject::sender()->objectName();

    for(int k=0;k<categoryList.count();k++){

        if(QString::compare(objectName,categoryList[k]->objectName())==0){

            if(QString::compare(objectName,"Topping")==0){
                ui->addToppingButton->setEnabled(true);
            }

            if(categoryList.isEmpty()){
                currentCategory = "NULL";
            } else {
                currentCategory = categoryList.last()->getCategoryName();
            }

            for(int i=0;i<positionList.count();i++){

                if(QString::compare(currentCategory,"NULL")!=0){

                    if(QString::compare(currentCategory,positionList[i]->getCategoryID())==0){
                        positionList[i]->deleteLater();
                        positionList.removeAt(i);
                    }

                }
            }

            categoryList[k]->deleteLater();
            categoryList.removeAt(k);

            categoryCount--;

            repaintPositionList();

            break;
        }
    }

}

void MainWindow::showAddPositionWidget()
{
    qDebug()<<"SHOW ADD POSITION!";

    QString objectName = QObject::sender()->objectName();

    positionCreator* creatPositionWidget = new positionCreator(nullptr);
    creatPositionWidget->setObjectName(objectName);
    connect(creatPositionWidget,SIGNAL(savePosition(QString,QString)),this,SLOT(creatPosition(QString,QString)));
    creatPositionWidget->show();

    currentCategory = objectName;

    repaintPositionList();

    creatPositionWidget->setModal(true);


}

void MainWindow::creatPosition(QString name, QString cost)
{
    qDebug()<<"POSITION IS CREAT!";
    qDebug()<<"name="<<name;
    qDebug()<<"cost="<<cost;
    qDebug()<<"SHOW ADD POSITION!";

    QString objectName = QObject::sender()->objectName();

    _position = new positions(nullptr,name,cost);
    _position->setCategoryID(objectName);

    _position->show();

    positionList.append(_position);
    positionList.last()->setLayout(ui->positionLayout);

    ui->positionLayout->addWidget(positionList.last());

}

void MainWindow::categoryClicked(){

    qDebug()<<"categoryClicked()";
    QString objectName = QObject::sender()->objectName();
    currentCategory = objectName;
    repaintPositionList();
}

